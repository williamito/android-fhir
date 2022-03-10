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

import java.util.EnumSet
import org.cqframework.cql.cql2elm.CqlTranslatorOptions
import org.cqframework.cql.cql2elm.model.Model
import org.hl7.elm.r1.VersionedIdentifier
import org.opencds.cqf.cql.engine.data.DataProvider
import org.opencds.cqf.cql.engine.execution.CqlEngine
import org.opencds.cqf.cql.engine.terminology.TerminologyProvider
import org.opencds.cqf.cql.evaluator.CqlEvaluator
import org.opencds.cqf.cql.evaluator.builder.CqlEvaluatorBuilder
import org.opencds.cqf.cql.evaluator.cql2elm.content.LibraryContentProvider
import org.opencds.cqf.cql.evaluator.cql2elm.model.CacheAwareModelManager
import org.opencds.cqf.cql.evaluator.engine.execution.CacheAwareLibraryLoaderDecorator
import org.opencds.cqf.cql.evaluator.engine.terminology.PriorityTerminologyProvider

class CqlEvaluatorBuilderExt(val libraryContentProvider: LibraryContentProvider) :
  CqlEvaluatorBuilder() {
  private var stale = false

  override fun build(): CqlEvaluator {
    withLibraryContentProvider(libraryContentProvider)

    val defaultLibraryLoader = callHidden("buildLibraryLoader") as CacheAwareLibraryLoaderDecorator

    val libraryLoader =
      TranslatingLibraryLoaderExt(
        CacheAwareModelManager(
          getFieldValue("globalModelCache") as MutableMap<VersionedIdentifier, Model>?
        ),
        getFieldValue("libraryContentProviders") as List<LibraryContentProvider>,
        CqlTranslatorOptions.defaultOptions()
      )

    this.decorate(CacheAwareLibraryLoaderDecorator(libraryLoader))

    val terminologyProvider: TerminologyProvider =
      (callHidden("buildTerminologyProvider") as TerminologyProvider?)
        ?: PriorityTerminologyProvider(listOf())
    val dataProviders: Map<String, DataProvider> =
      callHidden("buildDataProviders", terminologyProvider) as Map<String, DataProvider>
    val engineOptions = EnumSet.of(CqlEngine.Options.EnableExpressionCaching)

    return CqlEvaluator(libraryLoader, dataProviders, terminologyProvider, engineOptions)
  }

  private fun getFieldValue(field: String): Any {
    return CqlEvaluatorBuilder::class
      .java
      .getDeclaredField(field)
      .apply { isAccessible = true }
      .get(this)!!
  }

  private fun callHidden(method: String): Any? {
    return CqlEvaluatorBuilder::class
      .java
      .getDeclaredMethod(method)
      .apply { isAccessible = true }
      .invoke(this)
  }

  private fun callHidden(method: String, arg: Any?): Any {
    return CqlEvaluatorBuilder::class
      .java
      .declaredMethods
      .first { it.name == method }
      .apply { isAccessible = true }
      .invoke(this, arg)!!
  }
}
