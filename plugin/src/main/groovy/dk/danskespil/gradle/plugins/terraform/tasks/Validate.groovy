package dk.danskespil.gradle.plugins.terraform.tasks

import org.gradle.api.file.FileCollection
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputFiles
import org.gradle.api.tasks.TaskAction
import org.gradle.process.ExecSpec

/**
 * Wraps cli terraform validate
 */

class Validate extends TerraformBaseTask implements TerraformVariables {
    // These inputfiles are the same for Validate and Plan
    @OutputFiles
    FileCollection oTerraformFiles = project.fileTree('.').include('*.tf').include('*.tpl')
    @InputFiles
    FileCollection iTerraformFiles = project.fileTree('.').include('*.tf').include('*.tpl')

    @TaskAction
    action() {
        commandLine.addToEnd('terraform', 'validate')
        addVariablesToEnd(commandLine)

        executor.executeExecSpec(this, { ExecSpec e ->
            e.commandLine this.commandLine
            e.workingDir project.projectDir
        })
    }

    @Override
    String getDescription() {
        return "Wraps cli terraform validate"
    }
}
