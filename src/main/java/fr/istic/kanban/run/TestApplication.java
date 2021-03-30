/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.istic.kanban.run;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import fr.istic.kanban.res.KanbanResource;
import fr.istic.kanban.res.TagResource;
import fr.istic.kanban.res.UserResource;
import io.swagger.jaxrs.config.BeanConfig;
import fr.istic.kanban.res.SectionResource; 
import fr.istic.kanban.res.FicheResource; 
 
public class TestApplication extends Application {
	
	 public TestApplication() {
		    BeanConfig beanConfig = new BeanConfig(); 
	        beanConfig.setVersion("1.0.2");
	        beanConfig.setSchemes(new String[]{"http"});
		    beanConfig.setBasePath("/api");
	        beanConfig.setHost("localhost:8002");
	        beanConfig.setResourcePackage("io.swagger.resources");
		    beanConfig.setScan(true);
		  }

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> clazzes = new HashSet<Class<?>>();
        clazzes.add(KanbanResource.class);
        clazzes.add(TagResource.class);
        clazzes.add(UserResource.class);
        clazzes.add(SectionResource.class);
        clazzes.add(FicheResource.class);
        clazzes.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        clazzes.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
        return clazzes;
    }

}
 