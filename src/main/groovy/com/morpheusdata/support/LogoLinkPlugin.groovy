/**
@author Chris Taylor
**/

package com.morpheusdata.support

import com.morpheusdata.core.Plugin
import com.morpheusdata.model.OptionType

class LogoLinkPlugin extends Plugin {

	@Override
	String getCode() {
		return 'logo-link-plugin'
	}

	@Override
	void initialize() {
		LogoLinkProvider provider = new LogoLinkProvider(this, morpheus)
		this.pluginProviders.put(provider.providerCode, provider)
		this.setName("Logo Link Configuration Plugin")
		this.setDescription("Configure the links for the header and footer")
		this.setAuthor("Morpheus")
		this.settings << new OptionType (
			name: 'Header Link',
			code: 'logo-link-plugin-header-url',
			fieldName: 'logoLinkPluginHeaderUrl',
			displayOrder: 0,
			fieldLabel: 'Header URL',
			helpText: 'The fully qualified or relative URL for the header logo. For example https://morpheusdata.com or /provisioning/instances',
			required: false,
			inputType: OptionType.InputType.TEXT
		)
		this.settings << new OptionType (
			name: 'Header Link',
			code: 'logo-link-plugin-footer-url',
			fieldName: 'logoLinkPluginFooterUrl',
			displayOrder: 1,
			fieldLabel: 'Footer URL',
			helpText: 'The fully qualified or relative URL for the footer logo. For example https://morpheusdata.com or /provisioning/instances',
			required: false,
			inputType: OptionType.InputType.TEXT
		)
	}

	@Override
	void onDestroy() {
	}
}
