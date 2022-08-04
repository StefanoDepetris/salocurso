package com.example.salo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.salo.core.Resource
import com.example.salo.repository.MovieRepository
import kotlinx.coroutines.Dispatchers


//Aca se prepara la informacion y prepara interfaz grafica.

//Al usar corrutinas con funciones suspend no pueden correr estos en el hilo principal.Por lo tanto debemos correrlo en otro hilo.Las suspend function no tienen un hilo despachador en donde
//en donde ejecutarse por eso nosotros debemos asignar este


class MovieViewModel(private val repo:MovieRepository):ViewModel() {

    //Las conrrutinas que se ejecutan en el viewmodel deben vivir hasta que el viewmodel muera
    fun fetchUMainScreenMovies() = liveData(Dispatchers.IO){
        //Tenemos 3 tipos de estado:Cargando, exito o estado de fallo.

        //EL emit emite el valor de nuestro estado para modificar el livedata cada uno se ejecuta secuencialmente.Esto se puede hacer dentro del repositorio
        emit(Resource.Loading())

        try {
            //Con triple devolvemos 3 llamadas, pero si queremos 4 tendriamos qeu crear un data class que aparecen como NTuple4
            emit(Resource.Success(Triple(repo.getUpComingMovies(), repo.getPopularMovies(), repo.getTopRateMovies())))
        }
        catch (e:Exception){
            emit(Resource.Failure(e))
        }
    }

    //Las conrrutinas que se ejecutan en el viewmodel deben vivir hasta que el viewmodel muera y hace el attach al dispatcher.main
//    fun fetchUMainScreenMovies() = liveData(viewModelScope.coroutineContext + Dispatchers.Main){
//        //Tenemos 3 tipos de estado:Cargando, exito o estado de fallo.
//
//        //EL emit emite el valor de nuestro estado para modificar el livedata cada uno se ejecuta secuencialmente.Esto se puede hacer dentro del repositorio
//        emit(Resource.Loading())
//
//        try {
//            //Con triple devolvemos 3 llamadas, pero si queremos 4 tendriamos qeu crear un data class que aparecen como NTuple4
//            emit(Resource.Success(Triple(repo.getUpComingMovies(), repo.getPopularMovies(), repo.getTopRateMovies())))
//        }
//        catch (e:Exception){
//            emit(Resource.Failure(e))
//        }
//    }



}
//Las instancias de viewmodel deben ser vacias pero tenemos la posibilidad de usar factory.
class MovieViewModelFactory(private val repo:MovieRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //Constructor de MovieRpository.
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
    }

}