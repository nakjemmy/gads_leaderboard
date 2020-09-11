package com.nakjemmy.leaderboard

import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.nakjemmy.leaderboard.`interface`.LeaderBoardService
import com.nakjemmy.leaderboard.data.LearningLeader
import com.nakjemmy.leaderboard.service.RetrofitServiceBuilder
import kotlinx.android.synthetic.main.content_project_submission.*
import kotlinx.android.synthetic.main.dialog_confirm.*
import kotlinx.coroutines.delay
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectSubmissionActivity : AppCompatActivity(),
    SubmitProjectDialogFragment.NoticeDialogListener {
    private lateinit var submitProjectDialogFragment: SubmitProjectDialogFragment
    private lateinit var emailAddress: String;
    private lateinit var firstName: String;
    private lateinit var lastName: String;
    private lateinit var projectUrl: String;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_project_submission)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        button_submit_final.setOnClickListener { _ ->
            if (validateInput()) {
                showConfirmDialog()
            } else {
                Toast.makeText(this, "Please enter valid information", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun showConfirmDialog() {
        submitProjectDialogFragment = SubmitProjectDialogFragment()
        submitProjectDialogFragment.show(
            supportFragmentManager,
            "confirm_project_submission"
        )
    }

    private fun showSuccessDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setView(R.layout.dialog_success)
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(R.drawable.rounded_rect_20)
        dialog.show()
    }

    private fun showErrorDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setView(R.layout.dialog_error)
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(R.drawable.rounded_rect_20)
        dialog.show()
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        submitProject()
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        submitProjectDialogFragment.dismiss()
    }

    private fun submitProject() {
        val request = RetrofitServiceBuilder.buildService(LeaderBoardService::class.java)
        val call = request.submitProject(emailAddress, firstName, lastName, projectUrl)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    submitProjectDialogFragment.dismiss()
                    Thread.sleep(500)
                    showSuccessDialog()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                submitProjectDialogFragment.dismiss()
                Thread.sleep(500)
                showErrorDialog()
            }

        })

    }

    private fun validateInput(): Boolean {
        emailAddress = edit_text_submission_email.text.toString();
        firstName = edit_text_submission_first_name.text.toString();
        lastName = edit_text_submission_last_name.text.toString();
        projectUrl = edit_text_submission_project_url.text.toString();

        val emailIsValid = Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()
        val projectUrlIsValid = Patterns.WEB_URL.matcher(projectUrl).matches()
        val nameIsValid = firstName.isNotEmpty() && lastName.isNotEmpty()
        return emailIsValid && projectUrlIsValid && nameIsValid
    }


}