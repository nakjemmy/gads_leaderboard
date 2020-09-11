package com.nakjemmy.leaderboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.nakjemmy.leaderboard.`interface`.LeaderBoardService
import com.nakjemmy.leaderboard.data.SkillIqLeader
import com.nakjemmy.leaderboard.service.RetrofitServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A fragment representing a list of Items.
 */
class SkillIqLeaderFragment : Fragment() {

    private var skillIqLeaders = mutableListOf<SkillIqLeader>()
    private lateinit var skillIqLeadersAdapter: MySkillIqLeaderRecyclerViewAdapter;
    private lateinit var spinner: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView =  inflater.inflate(
            R.layout.fragment_leader_list,
            container,
            false
        )
        val recyclerView: RecyclerView =
            rootView.findViewById(R.id.recycler_learning_leaders) as RecyclerView
        spinner = rootView.findViewById(R.id.progress_bar)

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.visibility = View.GONE;
        spinner.visibility = View.VISIBLE;

        fetchSkillIqLeaders(recyclerView)

        return rootView
    }

    private fun fetchSkillIqLeaders(recyclerView: RecyclerView) {
        val request = RetrofitServiceBuilder.buildService(LeaderBoardService::class.java)
        val call = request.getSkillIqLeaders()
        call.enqueue(object : Callback<List<SkillIqLeader>> {
            override fun onResponse(
                call: Call<List<SkillIqLeader>>,
                response: Response<List<SkillIqLeader>>
            ) {
                if (response.isSuccessful && activity != null) {
                    skillIqLeaders.addAll(response.body()!!)
                    skillIqLeadersAdapter = MySkillIqLeaderRecyclerViewAdapter(skillIqLeaders)
                    recyclerView.adapter = skillIqLeadersAdapter
                    Thread.sleep(1000)
                    recyclerView.visibility = View.VISIBLE;
                    spinner.visibility = View.GONE;
                }
            }

            override fun onFailure(call: Call<List<SkillIqLeader>>, t: Throwable) {
                Toast.makeText(context, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}