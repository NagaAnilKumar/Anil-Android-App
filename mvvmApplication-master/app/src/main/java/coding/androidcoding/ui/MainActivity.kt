package coding.androidcoding.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import coding.androidcoding.R
import coding.androidcoding.data.models.CardModel
import coding.androidcoding.util.Coroutines
import coding.androidcoding.util.hide
import coding.androidcoding.util.show
import com.google.gson.GsonBuilder
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {
    override val kodein by kodein()

    private val factory: MainViewModelFactory by instance();
    private  lateinit var cardAdapter: CardAdapter

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        initRecyclerView()
        bindUI()
    }

    private fun bindUI() = Coroutines.main {
        progress_bar.show()
        viewModel.cardList.await().observe(this, Observer {
            progress_bar.hide()
            cardAdapter.setData(it)
        })
    }

    private fun initRecyclerView() {
        cardAdapter = CardAdapter()

        recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = cardAdapter
        }

    }
}