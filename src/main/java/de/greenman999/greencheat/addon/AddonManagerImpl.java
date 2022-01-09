package de.greenman999.greencheat.addon;

import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class AddonManagerImpl implements AddonManager{

    private List<AddonInterface> loadedplugins = new ArrayList<>();

    public void start(){
        File[] files = new File(FabricLoader.getInstance().getGameDir() + "/greencheat/addons").listFiles();
        for(File f : files)
            loadAddon(f);
        for(AddonInterface ai : loadedplugins)
            ai.onEnable();
    }
    public void stop(){
        for(AddonInterface ai : loadedplugins)
            ai.onDisable();
    }
    public void loadAddon(File file) {
        try {
            JarFile jarFile = new JarFile(file);
            Manifest manifest = jarFile.getManifest();
            Attributes attrib = manifest.getMainAttributes();
            String main = attrib.getValue(Attributes.Name.MAIN_CLASS);

            String ADDONINTERFACECLASS_STRING = "de.greenman999.greencheat.addon.AddonInterface";
            URL urlFile = file.toURI().toURL();
            ClassLoader classLoader = new URLClassLoader(new URL[] { urlFile });
            Class cl = classLoader.loadClass(main);
            Class[] interfaces = cl.getInterfaces();
            boolean isAddon = false;
            for(int y = 0; y < interfaces.length && !isAddon; y++)
                if(interfaces[y].getName().equals(ADDONINTERFACECLASS_STRING))
                    isAddon = true;
            if(isAddon){
                AddonInterface plugin = (AddonInterface) cl.newInstance();
                loadedplugins.add(plugin);
            }

            jarFile.close();
            //classLoader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
