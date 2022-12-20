package com.example.batterysmart.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.batterysmart.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddToWalletBottomSheet : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.layout_payment_bottomsheet, container)
    }


    companion object {
        fun newInstance(): AddToWalletBottomSheet {
            val fragment = AddToWalletBottomSheet()
            return fragment
        }

    }
}