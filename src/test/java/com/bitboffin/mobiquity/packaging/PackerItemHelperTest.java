package com.bitboffin.mobiquity.packaging;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.bitboffin.mobiquity.packaging.helper.PackageItemHelper;
import com.bitboffin.mobiquity.packaging.model.PackageItem;
import com.google.common.collect.Multimap;

/**
 * Main test cases holder class.
 * Method names are refer to the method names of class that covered with tests.
 * Class name refer to the class that covered with tests.
 */


@SpringBootTest
public class PackerItemHelperTest {
    private PackageItemHelper packerTemplate;
    private final String fileName = "correctInput.txt";

    @Before
    public void setup() {
        packerTemplate = Mockito.spy(new PackageItemHelper());
    }

    @Test
    public void testGetFile() {
        File testFile = packerTemplate.getFile(fileName);
        assertEquals(testFile.getName(), fileName);
    }

    @Test
    public void testGetItems() {
        File file = packerTemplate.getFile(fileName);
        Multimap<String, PackageItem> items = packerTemplate.getPackageItems(file);
        assertNotNull(items);
        assertNotEquals(Collections.emptyMap(), items.asMap());
        assertEquals(items.size(), 25);
    }

    @Test
    public void testProcessItems() {
        File file = packerTemplate.getFile(fileName);
        Multimap<String, PackageItem> items = packerTemplate.getPackageItems(file);
        Map<String, ArrayList<PackageItem>> processedItems = packerTemplate.processPackageItems(items);

        assertNotNull(processedItems);
        assertEquals(processedItems.size(), 4);
        assertEquals(processedItems.keySet().toString(), "[56, 75, 8, 81]");
        assertEquals(processedItems.get("56").size() == 2, processedItems.get("75").size() == 2);
        assertEquals(processedItems.get("81").size(), 1);

        assertEquals(processedItems.get("8"), Collections.emptyList());
        assertTrue(processedItems.get("56").stream().anyMatch(item -> item.getPackageIndex().equals(3)));
        assertTrue(processedItems.get("56").stream().anyMatch(item -> item.getPackageIndex().equals(9)));
        assertTrue(processedItems.get("81").stream().anyMatch(item -> item.getPackageIndex().equals(3)));
        assertTrue(processedItems.get("75").stream().anyMatch(item -> item.getPackageIndex().equals(3)));
        assertTrue(processedItems.get("75").stream().anyMatch(item -> item.getPackageIndex().equals(5)));
    }

    @Test
    public void testGetOutput() {
        File file = packerTemplate.getFile(fileName);
        Multimap<String, PackageItem> items = packerTemplate.getPackageItems(file);
        Map<String, ArrayList<PackageItem>> processedItems = packerTemplate.processPackageItems(items);
        String output = packerTemplate.getPackageOutput(processedItems);
        String expectedOutput = ")3=tsoCegakcap ,84.87=thgieWegakcap ,3=xednIegakcap(metIegakcaP\n" + "-\n" + ")25=tsoCegakcap ,96.36=thgieWegakcap ,5=xednIegakcap(metIegakcaP,)61=tsoCegakcap ,89.3=thgieWegakcap ,3=xednIegakcap(metIegakcaP\n" + ")46=tsoCegakcap ,67.6=thgieWegakcap ,9=xednIegakcap(metIegakcaP,)01=tsoCegakcap ,51.34=thgieWegakcap ,3=xednIegakcap(metIegakcaP\n";
        assertEquals(expectedOutput, output);
    }

    @Test
    public void testPack() {
        packerTemplate.pack(fileName);

        verify(packerTemplate, times(1)).getFile(anyString());
        verify(packerTemplate, times(1)).getPackageItems(any(File.class));
        verify(packerTemplate, times(1)).processPackageItems(any());
        verify(packerTemplate, times(1)).getPackageOutput(anyMap());
    }
}
