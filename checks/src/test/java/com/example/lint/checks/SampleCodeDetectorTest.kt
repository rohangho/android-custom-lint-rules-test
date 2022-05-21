/*
 * Copyright (C) 2017 The Android Open Source Project
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
package com.example.lint.checks


import com.android.tools.lint.checks.infrastructure.TestFiles.java
import com.android.tools.lint.checks.infrastructure.TestFiles.manifest
import com.android.tools.lint.checks.infrastructure.TestLintTask.lint
import org.junit.Test

@Suppress("UnstableApiUsage")
class SampleCodeDetectorTest {
    @Test
    fun testBasic() {
        lint().files(manifest(
            """<manifest xmlns:tools="http://schemas.android.com/tools"
    xmlns:android="http://schemas.android.com/apk/res/android">

    <application>
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter android:label="@string/filter_view_example_gizmos">
                <action android:name="android.intent.action.VIEW" />
                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />
                <data android:scheme="example"
                    android:host="gizmos" />
            </intent-filter>

        </activity>
    </application>

</manifest>"""
        ).indented()).allowMissingSdk()
            .issues(SampleManifestDetector.ManifestIssue)
            .run()
            .expect(
                """ Beware you are adding a new Route link
                    """
            )
    }
}
