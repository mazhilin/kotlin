/*
 * Copyright 2010-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.config

import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.resolve.OverridingUtil
import org.jetbrains.kotlin.types.KotlinType
import org.jetbrains.kotlin.types.checker.NewKotlinTypeChecker
import org.jetbrains.kotlin.types.checker.RefineKotlinTypeChecker

class RefineKotlinTypeCheckerImpl(
    private val moduleDescriptor: ModuleDescriptor,
    languageVersionSettings: LanguageVersionSettings
) : RefineKotlinTypeChecker {
    private val isRefinementDisabled = !languageVersionSettings.getFlag(AnalysisFlags.useTypeRefinement)

    override fun isSubtypeOf(subtype: KotlinType, supertype: KotlinType): Boolean {
        if (isRefinementDisabled) return NewKotlinTypeChecker.isSubtypeOf(subtype, supertype)

        return NewKotlinTypeChecker.isSubtypeOf(subtype.refine(moduleDescriptor), supertype.refine(moduleDescriptor))
    }

    override fun equalTypes(subtype: KotlinType, supertype: KotlinType): Boolean {
        if (isRefinementDisabled) return NewKotlinTypeChecker.equalTypes(subtype, supertype)

        return NewKotlinTypeChecker.equalTypes(subtype.refine(moduleDescriptor), supertype.refine(moduleDescriptor))
    }

    override fun refineType(type: KotlinType): KotlinType {
        if (isRefinementDisabled) return type
        return type.refine(moduleDescriptor)
    }

    override fun refineSupertypes(
        classDescriptor: ClassDescriptor,
        moduleDescriptor: ModuleDescriptor
    ): Collection<KotlinType> {
        if (isRefinementDisabled) return classDescriptor.typeConstructor.supertypes
        return classDescriptor.typeConstructor.supertypes.map { it.refine(moduleDescriptor) }
    }

    override fun refineSupertypes(classDescriptor: ClassDescriptor): Collection<KotlinType> =
        refineSupertypes(classDescriptor, moduleDescriptor)

    override val overridingUtil = OverridingUtil.createWithRefinedTypeChecker(this)
}
