package com.nakjemmy.leaderboard.`interface`

import com.nakjemmy.leaderboard.data.LearningLeader
import com.nakjemmy.leaderboard.data.SkillIqLeader
import retrofit2.Call
import retrofit2.http.*

interface LeaderBoardService {
    @GET("api/hours")
    fun getLearningLeaders(): Call<List<LearningLeader>>

    @GET("api/skilliq")
    fun getSkillIqLeaders(): Call<List<SkillIqLeader>>

    @POST("https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    @FormUrlEncoded
    fun submitProject(
        @Field("entry.1824927963") emailAddress: String,
        @Field("entry.1877115667") firstName: String,
        @Field("entry.2006916086") lastName: String,
        @Field("entry.284483984") projectUrl: String,
    ): Call<Void>
}