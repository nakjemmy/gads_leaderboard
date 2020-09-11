package com.nakjemmy.leaderboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.nakjemmy.leaderboard.adapter.LeaderBoardAdapter
import kotlinx.android.synthetic.main.fragment_leader_board.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LeaderBoardFragment : Fragment() {
    private lateinit var leaderBoardAdapter: LeaderBoardAdapter
    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_leader_board, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        leaderBoardAdapter = LeaderBoardAdapter(this)
        viewPager = view.findViewById(R.id.leader_board_view_pager)
        viewPager.adapter = leaderBoardAdapter

        val tabLayout = view.findViewById<TabLayout>(R.id.leader_board_tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Learning Leaders"
                }
                1 -> {
                    tab.text = "Skill IQ Leaders"
                }
            }
        }.attach()


        button_submit_project.setOnClickListener { _ ->
            val intent = Intent(context, ProjectSubmissionActivity::class.java)
            startActivity(intent)
        }
    }
}