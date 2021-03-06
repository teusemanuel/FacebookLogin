/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.commons.facebooklogin.objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Mateus Emanuel Araújo on 20/03/16.
 * MA Solutions
 * teusemanuel@gmail.com
 */
public class PictureItem implements Serializable {

    @SerializedName("is_silhouette")
    private boolean isSilhouette;

    private String url;

    public boolean isSilhouette() {
        return isSilhouette;
    }

    public void setIsSilhouette(boolean isSilhouette) {
        this.isSilhouette = isSilhouette;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
