/*
 * Copyright (C) 2020 The Android Open Source Project
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


import com.android.tools.lint.client.api.UElementHandler
import com.android.tools.lint.detector.api.*
import com.android.tools.lint.detector.api.Detector.UastScanner
import org.jetbrains.uast.UElement
import org.jetbrains.uast.ULiteralExpression
import org.jetbrains.uast.evaluateString
import org.w3c.dom.Element

/**
 * Sample detector showing how to analyze Kotlin/Java code. This example
 * flags all string literals in the code that contain the word "lint".
 */
class SampleManifestDetector:Detector(),Detector.XmlScanner {
    override fun getApplicableElements(): Collection<String> {
        return listOf("data","intent-filter")
    }

    override fun visitElement(context: XmlContext, element: Element) {
        context.report(ManifestIssue,context.getLocation(element),"Beware a new link is being added")
    }



    companion object {

        @JvmField
        val ManifestIssue: Issue = Issue.create(
            id = "Manifest_Redirection",
            briefDescription = "A new Data Link Added",
            explanation = """
                    You are adding a new Data Link in Route Manager Be careful
                    """, // no need to .trimIndent(), lint does that automatically
            category = Category.MESSAGES,
            priority = 6,
            severity = Severity.ERROR,
            implementation = Implementation(
                SampleManifestDetector::class.java,
                Scope.MANIFEST_SCOPE
            )
        )
    }
}
