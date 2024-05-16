package com.example.firemind.ListPersonalizable

import Liked
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.firemind.R


class BlankFragmentLikedList : Fragment() {

    private var listaLiked = ArrayList<Liked>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {


        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        var view : View = inflater.inflate(R.layout.fragment_blank_liked_list, container, false)

        return view
    }

}