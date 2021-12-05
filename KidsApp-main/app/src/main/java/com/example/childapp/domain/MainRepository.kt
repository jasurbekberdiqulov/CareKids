package com.example.childapp.domain

import com.example.childapp.remote.api.ApiClientService
import com.example.childapp.remote.model.*
import javax.inject.Inject

class MainRepository @Inject constructor(private val api: ApiClientService) {

    suspend fun sendAllApps(appList: AppList) = api.sendAllApps(appList)


    suspend fun sendAllAppsStatistics(appStatisticList: AppStatisticList) =
        api.sendAllAppsStatistics(appStatisticList)


    suspend fun sendDeviceInfo(deviceInfo: DeviceInfo) = api.sendDeviceInfo(deviceInfo)


    suspend fun sendLocationToServer(location: Location?) = api.sendLocationToServer(location)


    suspend fun sendCallHistoryToServer(callLogs: CallLogs) = api.sendCallHistoryToServer(callLogs)

    suspend fun sendAllContactToServer(contactList: Contacts) =
        api.sendAllContactToServer(contactList)
}