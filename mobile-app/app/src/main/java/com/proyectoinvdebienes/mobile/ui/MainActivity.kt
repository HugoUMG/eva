package com.proyectoinvdebienes.mobile.ui

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proyectoinvdebienes.mobile.R

class MainActivity : AppCompatActivity() {

    private lateinit var loginPanel: View
    private lateinit var homePanel: View
    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var roleSpinner: Spinner
    private lateinit var loginMessage: TextView
    private lateinit var sessionText: TextView
    private lateinit var modulesRecycler: RecyclerView
    private lateinit var moduleTitle: TextView
    private lateinit var moduleDescription: TextView

    private var currentSession: UserSession? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()
        setupRoleSpinner()
        setupActions()
        renderLoggedOut()
    }

    private fun bindViews() {
        loginPanel = findViewById(R.id.loginPanel)
        homePanel = findViewById(R.id.homePanel)
        usernameInput = findViewById(R.id.usernameInput)
        passwordInput = findViewById(R.id.passwordInput)
        roleSpinner = findViewById(R.id.roleSpinner)
        loginMessage = findViewById(R.id.loginMessage)
        sessionText = findViewById(R.id.sessionText)
        modulesRecycler = findViewById(R.id.modulesRecycler)
        moduleTitle = findViewById(R.id.moduleTitle)
        moduleDescription = findViewById(R.id.moduleDescription)
    }

    private fun setupRoleSpinner() {
        val roles = UserRole.entries.map { it.name }
        roleSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, roles)
    }

    private fun setupActions() {
        findViewById<Button>(R.id.loginButton).setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString()
            val roleName = roleSpinner.selectedItem.toString()

            if (username.isBlank() || password.isBlank()) {
                loginMessage.text = "Completa usuario y contraseña"
                return@setOnClickListener
            }

            val role = UserRole.valueOf(roleName)
            currentSession = UserSession(username, role)
            renderLoggedIn(currentSession!!)
        }

        findViewById<Button>(R.id.logoutButton).setOnClickListener {
            currentSession = null
            renderLoggedOut()
        }
    }

    private fun renderLoggedOut() {
        loginPanel.visibility = View.VISIBLE
        homePanel.visibility = View.GONE
        loginMessage.text = "Inicia sesión para acceder a los módulos"
    }

    private fun renderLoggedIn(session: UserSession) {
        loginPanel.visibility = View.GONE
        homePanel.visibility = View.VISIBLE
        sessionText.text = "Sesión: ${session.username} (${session.role.name})"

        val modules = ModuleCatalog.modulesForRole(session.role)
        modulesRecycler.layoutManager = GridLayoutManager(this, 2)
        modulesRecycler.adapter = ModuleCardAdapter(modules) { module ->
            moduleTitle.text = module.title
            moduleDescription.text = module.description
        }

        modules.firstOrNull()?.let {
            moduleTitle.text = it.title
            moduleDescription.text = it.description
        }
    }
}
