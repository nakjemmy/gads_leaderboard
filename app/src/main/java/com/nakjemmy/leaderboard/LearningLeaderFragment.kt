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
import com.nakjemmy.leaderboard.data.LearningLeader
import com.nakjemmy.leaderboard.service.RetrofitServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A fragment representing a list of Items.
 */
class LearningLeaderFragment : Fragment() {
    private var learningLeaders = mutableListOf<LearningLeader>()
    private lateinit var learningLeadersAdapter: MyLearningLeaderRecyclerViewAdapter
    private lateinit var spinner: ProgressBar

    private fun fetchLearningLeaders(recyclerView: RecyclerView) {
        val request = RetrofitServiceBuilder.buildService(LeaderBoardService::class.java)
        val call = request.getLearningLeaders()
        call.enqueue(object : Callback<List<LearningLeader>> {
            override fun onResponse(
                call: Call<List<LearningLeader>>,
                response: Response<List<LearningLeader>>
            ) {
                if (response.isSuccessful && activity != null) {
                    learningLeaders.addAll(response.body()!!)
                    learningLeadersAdapter = MyLearningLeaderRecyclerViewAdapter(learningLeaders)
                    recyclerView.adapter = learningLeadersAdapter
                    Thread.sleep(1000)
                    recyclerView.visibility = View.VISIBLE;
                    spinner.visibility = View.GONE;
                }
            }

            override fun onFailure(call: Call<List<LearningLeader>>, t: Throwable) {
                Toast.makeText(context, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }


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

        fetchLearningLeaders(recyclerView)

        return rootView

    }

}