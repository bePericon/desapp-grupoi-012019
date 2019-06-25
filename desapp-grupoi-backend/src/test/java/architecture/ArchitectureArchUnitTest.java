package architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;

import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;


@AnalyzeClasses(packages = {"service"})
public class ArchitectureArchUnitTest {

    @ArchTest
    public static void rule1(JavaClasses classes) {

        ArchRule rule = ArchRuleDefinition.noClasses()
                .that().resideInAPackage("..service..")
                .should().beAnnotatedWith(Transactional.class);
        rule.check(classes);
    }
    
//    @Test
//    public void Services_should_only_be_accessed_by_Controllers() {
//        JavaClasses importedClasses = new ClassFileImporter().importPackages("com.mycompany.myapp");
//
//        ArchRule myRule = classes()
//            .that().resideInAPackage("..service..")
//            .should().onlyBeAccessed().byAnyPackage("..controller..", "..service..");
//
//        myRule.check(importedClasses);
//    }
    
    
}
