package br.iesb.mobile.netflics.ui.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.iesb.mobile.netflics.R
import br.iesb.mobile.netflics.databinding.FragmentForgotPasswordBinding
import br.iesb.mobile.netflics.domain.AppResult
import br.iesb.mobile.netflics.viewmodel.LoginViewModel

class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgotPasswordBinding
    private val viewmodel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.fragment = this
        binding.viewmodel = viewmodel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel.result.observe(viewLifecycleOwner) {
            when (it) {
                is AppResult.Success -> {
                    requireActivity().finish()
                    Toast.makeText(context, getText(R.string.reset_password_email_sent), Toast.LENGTH_LONG).show()
                }
                is AppResult.Error -> Toast.makeText(context, it.message, Toast.LENGTH_LONG).show()
            }
        }
    }

}