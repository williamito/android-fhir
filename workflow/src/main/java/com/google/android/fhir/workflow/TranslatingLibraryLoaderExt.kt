/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.fhir.workflow

import org.cqframework.cql.cql2elm.CqlTranslatorOptions
import org.cqframework.cql.cql2elm.ModelManager
import org.cqframework.cql.elm.execution.Library
import org.opencds.cqf.cql.evaluator.cql2elm.content.LibraryContentProvider
import org.opencds.cqf.cql.evaluator.engine.execution.TranslatingLibraryLoader

class TranslatingLibraryLoaderExt(
  modelManager: ModelManager,
  libraryContentProviders: List<LibraryContentProvider>,
  translatorOptions: CqlTranslatorOptions
) : TranslatingLibraryLoader(modelManager, libraryContentProviders, translatorOptions) {
  override fun translatorOptionsMatch(library: Library): Boolean {
    return true
  }
}
