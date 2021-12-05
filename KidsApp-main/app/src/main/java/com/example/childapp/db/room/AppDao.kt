package com.example.childapp.db.room

import androidx.room.*
import com.example.childapp.function.keylog.KeyLog

@Dao
interface AppDao {
    //camPath save into db
    @Query("SELECT * FROM child_age")
    fun getChild(): Child

    @Insert
    fun addChild(vararg child: Child)

    @Delete
    fun deleteChild(child: Child)

    //Politcs
    @Query("SELECT * FROM code")
    fun getPolitics(): List<Code>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConfirmCode(code: Code)

    @Delete()
    fun deleteCode(code: Code)

    @Update
    fun updateCode(code: Code)


    //config service
    @Query("SELECT * FROM error_apps")
    fun getErrorToDb(): List<ErrorApp>

    @Insert
    fun insertErrorToDb(vararg errorApp: ErrorApp)

    @Delete
    fun deleteErrorFromDb(errorApp: ErrorApp)

    @Update
    fun updateErrorToDb(errorApp: ErrorApp)

    @Insert
    fun insertSendDateToDb(vararg sendDate: SendDate)

    // @Query("UPDATE sender_module_info SET moduleSendDate = :senderModuleInfo WHERE id = 1;")
    @Update
    fun updateSendDateToDb(sendDate: SendDate)

    @Query("SELECT * FROM send_dates")
    fun getSendDateInfoToDb(): SendDate

    @Query("SELECT * FROM send_dates WHERE moduleName LIKE :className")
    fun findBySendDateByName(className: String): SendDate

    //keylog save into db
    @Query("SELECT * FROM keylogs")
    fun getAllKeylog(): List<KeyLog>

    @Insert
    fun insertKeylog(vararg keyLog: KeyLog)

    @Query("DELETE FROM keylogs")
    fun deleteAllKeylog()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertApps(app: App)

    @Update
    fun updateAppsStatus(app: App)

    @Query("UPDATE apps SET updateField=:updateFieldStatus  WHERE id=:appId ")
    fun updateAppsUpdateField(appId: String, updateFieldStatus: Boolean)

    //camPath save into db
    @Query("SELECT * FROM apps")
    fun getAllApps(): List<App>

    @Query("Select * from apps where packageName like:packageName")
    fun findByPackageName(packageName: String): App

}