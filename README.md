# gradleprojectbug
possible intellijIdea bug related to gradle project

[bug/feature] Resources are not always copied into output folder.


IntelliJ IDEA 2017.2
Build #IC-172.3317.76, built on July 15, 2017
JRE: 1.8.0_152-release-915-b5 x86_64
JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
Mac OS X 10.9.5IntelliJ IDEA 2017.2
               Build #IC-172.3317.76, built on July 15, 2017
               JRE: 1.8.0_152-release-915-b5 x86_64
               JVM: OpenJDK 64-Bit Server VM by JetBrains s.r.o
               Mac OS X 10.9.5


Steps to reproduce :

1.  Pull project from

    https://github.com/SqAutoTestTeam/gradleprojectbug

    This project cannot be built with gradle and only demonstrates a problem I've encountered.

2. Open project in IDE IntellijIDEA

3. From IDE call the only test method of ru.idea.possiblebug.TestWithResource class (testResources())
   right click on the body of test method --> popup menu -->  run testResources()

4. Test fails due to not found resources
     (resource with path res/file.txt is not found. search from class class ru.idea.possiblebug.TestWithResource)

5. Go to module settings
   Project panel ->  click on module "module" -> press F4 ->
     setup output path and test output path to directories where normally gradle puts its outputs

     [moduledir]/build/classes/main  and [moduledir]/build/classes/test
     at the moment these pathes are already set


6.  go to menu Intellij IDEA --> preferences --> compile --> resource patterns

    at the moment this setting is
    !?*.java;!?*.form;!?*.class;!?*.groovy;!?*.scala;!?*.flex;!?*.kt;!?*.clj;!?*.aj

    --> left click on the text field with resource pattern value --> press F1

    Shown help page
    https://www.jetbrains.com/help/idea/2017.2/compiler.html?utm_medium=help_link&utm_source=from_product&utm_campaign=IC&utm_content=2017.2

    says that
    ... In this field, specify the regular expression that describes the files that should be recognized as
    resources and, consequently, copied to the output directory...

    The expression setting itself says that EVERYTHING recognized as resource and therefore copied to the output
    directory but except for the files with extensions java, form, class, groovy, scala, flex, kt, clj and aj
    (which are compiled)


7.  In case of a simple "Hello world" project - with no gradle support those files that are situated in
    the code base and are not (java, form, class, groovy, scala, flex, kt, clj and aj)
    are copied to output directory - and as result the same test would not fail in a simple project


8.   6 or 7 years ago in previouse versions of IDE the setting
     Intellij IDEA --> preferences --> compile --> resource patterns
     did not permit to copy a file to output by default. I remember that I had to change default value to achieve
     copying resources to output directory. Later in newer IDE versions the default value had been changed so every
     not explicitly excluded files are copied to output folder.

9. Copy file  (file.txt) to directory  resources/ru/idea/possiblebug/res in the module
   resources/ru/idea/possiblebug/res/file.txt
   (what is not actually done in this project)

10. Run the test - it is successful  -
   But somehow another out (module/out) directory is created.
   Judging by the common sense it mustn't be so. it must by copied into build/classes/test ...  directory



Whether or not it is a bug ? I do not know. It is debatable and makes questions, which, in turn, make finding the "right"
answer even more difficult.
https://stackoverflow.com/questions/4830547/why-are-project-layout-resources-are-kept-separate-from-java-sources

On the one hand there is Gradle (Maven) convention that dictates default project layout.
https://docs.gradle.org/current/userguide/java_plugin.html#sec:java_project_layout

On the other hand NONE tells what the restrictions for source code are.

Definition given in
http://searchmicroservices.techtarget.com/definition/source-code
as example says :
Source code is the fundamental component of a computer program that is created by a programmer.
It can be read and easily understood by a human being.


another definition given in wikipedia
https://en.wikipedia.org/wiki/Source_code
says:

"In computing, source code is any collection of computer instructions, possibly with comments,
written using a human-readable programming language, usually as ordinary text."

Does not give any strict limitations to the source code, as well.

to summarize a source code is a component of computer programm created by programmer that can be read and EASILY
understood (by human)

In my case file.txt can as well be considered a source code. A Source code that is
not compiled but, however, used when test is being run.

Apart of the main question i'd add that the entire package ru/idea/possiblebug can easily be moved to
another location with all its content without breaking the test logic
because the it is self contained and uses relative paths to elements within itself.

In case of usage of predefined resources/ru/idea/possiblebug/res/file.txt it would be more difficult. You'd have to move
class and resource from two folders.

Anyway if you find this a bug please fix it.
If not please consider making it possible to use resources situated near the source code.


I will provide you with more info should it be required.
