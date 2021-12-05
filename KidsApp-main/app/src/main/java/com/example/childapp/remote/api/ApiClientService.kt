package com.example.childapp.remote.api

import com.example.childapp.remote.model.*
import com.example.childapp.ui.splash.start.code.model.CodeRequest
import com.example.childapp.ui.splash.start.code.model.CodeResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface ApiClientService {


    @POST
    suspend fun checkParentCode(codeRequest: CodeRequest): Response<CodeResponse>

    @POST
    suspend fun sendNewToParent(): Response<CodeResponse>


    @POST("api/saveSms")
    suspend fun sendAllSmsToServer(@Body smsListList: SmsList): Response<Message>

    @Multipart
    @POST("api/saveFile")
    suspend fun sendFileToServer(
        @Part("clientID") clientID: RequestBody,
        @Part("name") filename: RequestBody,
        @Part("path") path: RequestBody,
        @Part("filesize") filesize: RequestBody,
        @Part file: MultipartBody.Part,
        @Part("type") type: RequestBody,
        @Part("date") time: RequestBody
    ): Response<Message>


    @POST("api/saveLocation ")
    suspend fun sendLocationToServer(@Body location: Location?): Response<Message>


    @POST("api/saveCall")
    suspend fun sendCallHistoryToServer(@Body callLogs: CallLogs?): Response<Message>

    @POST("api/saveContact")
    suspend fun sendAllContactToServer(@Body contactList: Contacts?): Response<Message>


    @POST("api/simInfo")
    suspend fun sendSimInfo(@Body sim: Sim?): Response<Message>


    @POST("api/saveApps")
    suspend fun sendAllApps(@Body appList: AppList?): Response<Message>

    @POST("api/saveUsage")
    suspend fun sendAllAppsStatistics(@Body appStatisticList: AppStatisticList?): Response<Message>


    @POST("api/saveInfo")
    suspend fun sendDeviceInfo(@Body deviceInfo: DeviceInfo): Response<Message>


}