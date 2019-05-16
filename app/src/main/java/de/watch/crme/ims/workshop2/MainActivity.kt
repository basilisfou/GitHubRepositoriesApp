package de.watch.crme.ims.workshop2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import de.watch.crme.ims.workshop2.adapter.RepositoryAdapter
import de.watch.crme.ims.workshop2.adapter.RepositoryOnClickListener
import de.watch.crme.ims.workshop2.api.GithubService
import de.watch.crme.ims.workshop2.api.GithubServiceHelper
import de.watch.crme.ims.workshop2.model.Repository
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import android.content.Intent
import android.net.Uri


class MainActivity : AppCompatActivity() {

    private var service : GithubService? = GithubServiceHelper.getApiService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repositoryList.layoutManager = LinearLayoutManager(this)

        getRepositories()

    }

    private fun getRepositories(){
       val call = service?.getRepositories()

        call?.enqueue(object : Callback<List<Repository>>{
            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {

                progressBar.visibility = View.GONE

                Toast.makeText(this@MainActivity,R.string.failure,Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {

                progressBar.visibility = View.GONE

                if(response.isSuccessful){

                    loadAdapter(response.body())

                    listSizeTV.text = getString(R.string.size_list,response.body()?.size.toString())

                } else {
                    Toast.makeText(this@MainActivity,R.string.error,Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    private fun loadAdapter(list : List<Repository>?){
        if(list == null){
            return
        }

        val onClickListener = object : RepositoryOnClickListener{

            override fun onClickListener(url: String) {
                openExternalBrowser(url)
            }

        }

        repositoryList.adapter = RepositoryAdapter(list, onClickListener)
    }

    private fun openExternalBrowser(url : String){
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }
}
