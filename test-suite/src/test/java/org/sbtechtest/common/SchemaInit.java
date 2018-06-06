package org.sbtechtest.common;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

public class SchemaInit {
	public JsonSchemaFactory jsonSchemaFactoryObj;
	public SchemaInit() {
		jsonSchemaFactoryObj = JsonSchemaFactory.newBuilder()
				.setValidationConfiguration(
						ValidationConfiguration.newBuilder()
						.setDefaultVersion(SchemaVersion.DRAFTV4).freeze())
						.freeze();
	}



}