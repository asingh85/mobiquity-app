package com.bitboffin.mobiquity.packaging;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.bitboffin.mobiquity.packaging.exception.PackageAPIException;
import com.bitboffin.mobiquity.packaging.helper.DefaultPackaging;
import com.bitboffin.mobiquity.packaging.helper.PackageItemHelper;
import com.bitboffin.mobiquity.packaging.helper.PackageService;
import com.bitboffin.mobiquity.packaging.model.PackageItem;
import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;

/**
 * Parametrized test that covers the different types of exception cases.
 * @param <PackageItemHelper>
 */
@RunWith(JUnitParamsRunner.class)
public class PackageAPIExceptionTest {
    
	@Rule
    public ExpectedException expectedException = ExpectedException.none();
    
	PackageItemHelper packerTemplate = new PackageItemHelper();
    
    @Test(expected = PackageAPIException.class)
    @FileParameters("./src/test/resources/fileNames.csv")
    public void testAllCasesExceptionThrown(String fileName) {
        File file = packerTemplate.getFile(fileName);
        packerTemplate.getPackageItems(file);
    }

 @Test
    @FileParameters("./src/test/resources/fileNamesToMessages.csv")
    public void testAllCasesCorrectExceptionMessageThrown(String fileName, String exceptionMessage) {
        expectedException.expect(PackageAPIException.class);
        expectedException.expectMessage(exceptionMessage);

        File file = packerTemplate.getFile(fileName);
        packerTemplate.getPackageItems(file);
    }
    
}
