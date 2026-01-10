package mk.ukim.finki.quizapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import mk.ukim.finki.quizapp.data.remote.RemoteQuizDataSource
import mk.ukim.finki.quizapp.data.remote.api.QuizApiProvider
import mk.ukim.finki.quizapp.data.repository.QuizRepository
import mk.ukim.finki.quizapp.utils.NetworkConnectivity

class QuizViewModelFactory(private val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return modelClass.getConstructor(QuizRepository::class.java)
            .newInstance(QuizRepository(
                RemoteQuizDataSource(QuizApiProvider.getQuizApi()),
                NetworkConnectivity(context)
            ))
    }
}