package com.morpheusdata.support

import com.morpheusdata.core.AbstractGlobalUIComponentProvider
import com.morpheusdata.core.MorpheusContext
import com.morpheusdata.core.Plugin
import com.morpheusdata.model.Account
import com.morpheusdata.model.ContentSecurityPolicy
import com.morpheusdata.model.User
import com.morpheusdata.views.HTMLResponse
import com.morpheusdata.views.ViewModel
import com.morpheusdata.model.UIScope
import com.morpheusdata.model.Permission
import groovy.json.*

class LogoLinkProvider extends AbstractGlobalUIComponentProvider {
	Plugin plugin
	MorpheusContext morpheus

	LogoLinkProvider(Plugin plugin, MorpheusContext context) {
		this.plugin = plugin
		this.morpheus = context
	}
	
	@Override
	MorpheusContext getMorpheus() {
		morpheus
	}

	@Override
	Plugin getPlugin() {
		plugin
	}

	@Override
	String getCode() {
		'logoLinkProvider'
	}
	
	String getProviderCode() {
		'logoLinkProvider'
	}

	@Override
	String getName() {
		'Logo Link Configuration Provider'
	}
	
	String getProviderName() {
		'Logo Link Configuration Provider'
	}

	@Override
	HTMLResponse renderTemplate(User user, Account account) {
		ViewModel<String> model = new ViewModel<String>()
		def nonse = morpheus.getWebRequest().getNonceToken()
		model.object = [:]
		
		try {
			def settings = getSettings()
			if(settings.logoLinkPluginHeaderUrl) {
				model.object.logoLinkPluginHeaderUrl = settings.logoLinkPluginHeaderUrl
			}
			if(settings.logoLinkPluginFooterUrl) {
				model.object.logoLinkPluginFooterUrl = settings.logoLinkPluginFooterUrl
			}
		} catch (Exception e) {
			println "Unable to obtain plugin settings"
		}

		model.object.nonse = nonse.toString()
		model.object.accountId = account.id?.toString()
		getRenderer().renderTemplate("hbs/linkgen", model)
	}

	@Override
	Boolean show(User user, Account account) {
		def show = true
		return show
	}

	@Override
	ContentSecurityPolicy getContentSecurityPolicy() {
		def csp = new ContentSecurityPolicy()
		csp
	}
	
	private getSettings() {
		def settings = this.morpheus.getSettings(this.plugin)
		def settingsOutput = ""
		settings.subscribe(
			{ outData -> 
				settingsOutput = outData
			},
			{ error ->
				println error.printStackTrace()
			}
		)

		JsonSlurper slurper = new JsonSlurper()
		def settingsJson = slurper.parseText(settingsOutput)
		return settingsJson
}
	
	
}
