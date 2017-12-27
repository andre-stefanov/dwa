package de.andrestefanov.dwa;

import de.andrestefanov.dwa.model.Action;
import de.andrestefanov.dwa.model.Attachment;
import de.andrestefanov.dwa.model.Message;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.ArrayList;

import static de.andrestefanov.dwa.Application.*;

@Component
public class Sender {

    private SlackService slackService;

    public Sender() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor())
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    Request newRequest;

                    newRequest = request.newBuilder()
                            .addHeader("Authorization", "Bearer " + SLACK_BOT_TOKEN)
                            .build();
                    return chain.proceed(newRequest);
                })
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://slack.com/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        slackService = retrofit.create(SlackService.class);
    }

    public void sendInspectionRequest() {
        Message message = new Message();
        message.text = SLACK_QUESTION_TEXT;
        message.channel = SLACK_CHANNEL_ID;
        message.username = SLACK_BOT_NAME;

        message.attachments = new ArrayList<>();

        Attachment attachment = new Attachment();
        attachment.callbackId = "dwa";
        attachment.fallback = "error";
        attachment.actions = new ArrayList<>();
        message.attachments.add(attachment);

        Action positive = new Action();
        positive.type = "button";
        positive.name = "dwa";
        positive.value = "positive";
        positive.text = "Working";
        positive.style = "primary";
        attachment.actions.add(positive);

        Action negative = new Action();
        negative.type = "button";
        negative.name = "dwa";
        negative.value = "negative";
        negative.text = "Broken";
        negative.style = "danger";
        attachment.actions.add(negative);

        try {
            slackService.sendMessage(message).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeMessage(String messageTs) {
        Message message = new Message();
        message.channel = SLACK_CHANNEL_ID;
        message.ts = messageTs;

        try {
            slackService.deleteMessage(message).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendCurrentStatus(long days) {
        Message message = new Message();
        message.channel = SLACK_CHANNEL_ID;
        message.username = SLACK_BOT_NAME;

        message.attachments = new ArrayList<>();

        Attachment attachment = new Attachment();
        attachment.fallback = days + " days without an accident";
        if (!StringUtils.isEmpty(HOST_URL)) {
            attachment.imageUrl = HOST_URL + "/images/" + days + ".jpg";
        }

        message.attachments.add(attachment);

        try {
            slackService.sendMessage(message).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
