package metest.core;

import net.minecraftforge.gradle.GradleStartCommon;

import java.util.List;
import java.util.Map;

public class GradleStartTestServer extends GradleStartCommon {

    // public static void main(String[] args) throws Throwable {
    //     (new GradleStartTestServer()).launch(args);
    // }

    @Override
    public void launch(String[] args) throws Throwable {
        super.launch(args);
    }

    @Override
    protected String getTweakClass() {
        return "";
    }

    @Override
    protected String getBounceClass() {
        return "net.minecraft.launchwrapper.Launch";
    }

    @Override
    protected void preLaunch(Map<String, String> argMap, List<String> extras) {
    }

    @Override
    protected void setDefaultArguments(Map<String, String> argMap) {
    }
}