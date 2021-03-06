package com.dlecan.reflections;

import java.io.IOException;
import java.net.URL;
import java.util.jar.JarFile;

import org.jboss.vfs.VirtualFile;
import org.reflections.ReflectionsException;
import org.reflections.vfs.SystemDir;
import org.reflections.vfs.Vfs;
import org.reflections.vfs.ZipDir;

/**
 * Code taken here: http://code.google.com/p/reflections/wiki/JBossIntegration
 * 
 * @author dlecan
 * 
 */
public final class ReflectionJBoss7Vfs {

	public static final void initVfs() {

		Vfs.addDefaultURLTypes(new Vfs.UrlType() {
			public boolean matches(URL url) {
				return url.getProtocol().equals("vfs");
			}

			public Vfs.Dir createDir(URL url) {
				VirtualFile content;
				try {
					content = (VirtualFile) url.openConnection().getContent();
				} catch (Throwable e) {
					throw new ReflectionsException(
							"could not open url connection as VirtualFile ["
									+ url + "]", e);
				}

				Vfs.Dir dir = null;
				try {
					dir = createDir(new java.io.File(content.getPhysicalFile()
							.getParentFile(), content.getName()));
				} catch (IOException e) { /* continue */
				}
				if (dir == null) {
					try {
						dir = createDir(content.getPhysicalFile());
					} catch (IOException e) { /* continue */
					}
				}
				return dir;
			}

			Vfs.Dir createDir(java.io.File file) {
				try {
					return file.exists() && file.canRead() ? file.isDirectory() ? new SystemDir(
							file) : new ZipDir(new JarFile(file))
							: null;
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		});

	}

}
