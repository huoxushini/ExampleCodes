package com.example.examplecodes;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.examplecodes.databinding.FragmentMainBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements  View.OnClickListener {

    //绑定页面
    private FragmentMainBinding binding;
    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_main, container, false);
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_main,container,false);
        binding.setLifecycleOwner(requireActivity());
        binding.btnListview.setOnClickListener(this);
        return binding.getRoot();

    }
    @Override
    public void onClick(View view) {
        if(view.getId()==binding.btnListview.getId())
        {
            Log.d("liuyu","ListView");
            NavController controller= Navigation.findNavController(view);
            controller.navigate(R.id.action_mainFragment_to_listviewActivity2);
        }
    }
}
