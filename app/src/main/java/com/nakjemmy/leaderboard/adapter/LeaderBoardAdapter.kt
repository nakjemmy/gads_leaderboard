package com.nakjemmy.leaderboard.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nakjemmy.leaderboard.LEARNING_LEADER_FRAGMENT_INDEX
import com.nakjemmy.leaderboard.LearningLeaderFragment
import com.nakjemmy.leaderboard.SKILL_IQ_LEADER_FRAGMENT_INDEX
import com.nakjemmy.leaderboard.SkillIqLeaderFragment

class LeaderBoardAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            LEARNING_LEADER_FRAGMENT_INDEX -> LearningLeaderFragment()
            SKILL_IQ_LEADER_FRAGMENT_INDEX -> SkillIqLeaderFragment()
            else -> LearningLeaderFragment()
        }
    }


}