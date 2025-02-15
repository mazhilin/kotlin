/*
 * Copyright 2010-2017 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.idea.scratch.ui

import com.intellij.ide.scratch.ScratchFileService
import com.intellij.ide.scratch.ScratchRootType
import com.intellij.openapi.components.ProjectComponent
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiManager
import org.jetbrains.kotlin.idea.scratch.*

class ScratchFileHook(val project: Project) : ProjectComponent {

    override fun projectOpened() {
        project.messageBus.connect(project).subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, ScratchEditorListener())
    }

    override fun projectClosed() {
        getAllEditorsWithScratchPanel(project).forEach { (editor, _) -> editor.removeScratchPanel() }
    }

    private inner class ScratchEditorListener : FileEditorManagerListener {
        override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
            if (!isPluggable(file)) return

            val editor = getEditorWithoutScratchPanel(source, file) ?: return

            ScratchTopPanel.createPanel(project, file, editor)

            ScratchFileAutoRunner.addListener(project, editor)
        }

        override fun fileClosed(source: FileEditorManager, file: VirtualFile) {}
    }

    private fun isPluggable(file: VirtualFile): Boolean {
        if (!file.isValid) return false
        if (!(ScratchFileService.getInstance().getRootType(file) is ScratchRootType || file.isKotlinWorksheet)) return false
        val psiFile = PsiManager.getInstance(project).findFile(file) ?: return false
        return ScratchFileLanguageProvider.get(psiFile.fileType) != null
    }
}
