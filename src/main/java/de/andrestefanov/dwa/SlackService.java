package de.andrestefanov.dwa;

import de.andrestefanov.dwa.model.Message;
import de.andrestefanov.dwa.model.MessageResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SlackService {

    @POST("chat.postMessage")
    Call<MessageResponse> sendMessage(@Body Message message);

    @POST("chat.delete")
    Call<ResponseBody> deleteMessage(@Body Message message);
}
