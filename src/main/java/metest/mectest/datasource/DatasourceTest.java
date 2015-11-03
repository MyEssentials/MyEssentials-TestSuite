package metest.mectest.datasource;

import metest.base.TestModule;
import metest.base.exception.TestException;
import metest.mectest.Config;
import metest.mectest.MECTest;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class DatasourceTest extends TestModule {

    public static final DatasourceTest instance = new DatasourceTest();

    private FakeDatasource datasource;
    public List<String> blockNames = new ArrayList<String>();

    @Override
    public void test() throws TestException {
        shouldInitDatabase();
        shouldDeleteAllInBlockTypesTable();
        shouldSaveInBlockTypesTable();
        shouldLoadAllInBlockTypesTable();
        allLoadedElementsShouldBeValid();
    }

    private void allLoadedElementsShouldBeValid() {
        for(String blockName : blockNames) {
            Block block = Block.getBlockFromName(blockName);
            ASSERT(block != null);
        }
    }

    private void shouldLoadAllInBlockTypesTable() {
        ASSERT(datasource.loadAll());
    }

    private void shouldInitDatabase() {
        datasource = new FakeDatasource(MECTest.instance.LOG, Config.instance, new FakeSchema());
    }

    private void shouldDeleteAllInBlockTypesTable() {
        ASSERT(datasource.deleteBlocks());
    }

    private void shouldSaveInBlockTypesTable() {
        for (Object aBlock : Block.blockRegistry) {
            Block block = (Block) aBlock;
            ASSERT(datasource.saveBlock(block.getUnlocalizedName(), block.getLightValue(), 0.0F));
        }
        ASSERT(datasource.saveBlock("SHOULD BE INVALID FUCK YOU", 10, 0.0F));
    }


}
