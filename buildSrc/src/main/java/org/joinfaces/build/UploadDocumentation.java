/*
 * Copyright 2016-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.joinfaces.build;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import org.gradle.api.DefaultTask;
import org.gradle.api.GradleException;
import org.gradle.api.file.RegularFileProperty;
import org.gradle.api.provider.Property;
import org.gradle.api.publish.plugins.PublishingPlugin;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.TaskAction;

/**
 * @author Lars Grefer
 */
@SuppressWarnings("UnstableApiUsage")
public class UploadDocumentation extends DefaultTask {

	@InputFile
	private final RegularFileProperty inputFile = newInputFile();

	@Input
	private final Property<String> version = getProject().getObjects().property(String.class);

	@Input
	private final Property<String> username = getProject().getObjects().property(String.class);

	@Input
	private final Property<String> password = getProject().getObjects().property(String.class);

	public UploadDocumentation() {
		setGroup(PublishingPlugin.PUBLISH_TASK_GROUP);
		this.version.set(getProject().provider(() -> getProject().getVersion().toString()));
	}

	@TaskAction
	public void upload() throws IOException {
		HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> getLogger().info(message.contains("Authorization") ? "Authorization" : message));
		loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

		OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.addInterceptor(loggingInterceptor)
				.build();

		Request request = new Request.Builder()
				.put(RequestBody.create(MediaType.get("application/zip"), this.inputFile.get().getAsFile()))
				.header("Authorization", Credentials.basic(this.username.get(), this.password.get()))
				.url("https://docs.joinfaces.org/api/" + this.version.get())
				.build();

		Call call = okHttpClient.newCall(request);

		Response response = call.execute();

		if (!response.isSuccessful()) {
			getLogger().error(response.message());
			getLogger().error(response.body().string());
			throw new GradleException(response.message());
		}
	}

	public RegularFileProperty getInputFile() {
		return this.inputFile;
	}

	public Property<String> getVersion() {
		return this.version;
	}

	public Property<String> getUsername() {
		return this.username;
	}

	public Property<String> getPassword() {
		return this.password;
	}
}
