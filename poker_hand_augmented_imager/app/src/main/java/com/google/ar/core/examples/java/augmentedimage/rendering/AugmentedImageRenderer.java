/*
 * Copyright 2018 Google Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.ar.core.examples.java.augmentedimage.rendering;

import android.content.Context;
import com.google.ar.core.Anchor;
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.Pose;
import com.google.ar.core.examples.java.augmentedimage.rendering.ObjectRenderer.BlendMode;
import java.io.IOException;

/** Renders an augmented image. */
public class AugmentedImageRenderer {
  private static final String TAG = "AugmentedImageRenderer";

  private static final float TINT_INTENSITY = 0.1f;
  private static final float TINT_ALPHA = 1.0f;
  private static final int[] TINT_COLORS_HEX = {
    0x000000, 0xF44336, 0xE91E63, 0x9C27B0, 0x673AB7, 0x3F51B5, 0x2196F3, 0x03A9F4, 0x00BCD4,
    0x009688, 0x4CAF50, 0x8BC34A, 0xCDDC39, 0xFFEB3B, 0xFFC107, 0xFF9800,
  };

  private final ObjectRenderer imageFrameUpperLeft = new ObjectRenderer();
  private final ObjectRenderer imageFrameUpperRight = new ObjectRenderer();
  private final ObjectRenderer imageFrameLowerLeft = new ObjectRenderer();
  private final ObjectRenderer imageFrameLowerRight = new ObjectRenderer();
  private final ObjectRenderer heart = new ObjectRenderer();
  private final ObjectRenderer spade = new ObjectRenderer();
  private final ObjectRenderer club = new ObjectRenderer();
  private final ObjectRenderer diamond = new ObjectRenderer();


  public AugmentedImageRenderer() {}

  public void createOnGlThread(Context context) throws IOException {

    imageFrameUpperLeft.createOnGlThread(
        context, "models/frame_upper_left.obj", "models/frame_base.png");
    imageFrameUpperLeft.setMaterialProperties(0.0f, 3.5f, 1.0f, 6.0f);
    imageFrameUpperLeft.setBlendMode(BlendMode.SourceAlpha);

    imageFrameUpperRight.createOnGlThread(
        context, "models/frame_upper_right.obj", "models/frame_base.png");
    imageFrameUpperRight.setMaterialProperties(0.0f, 3.5f, 1.0f, 6.0f);
    imageFrameUpperRight.setBlendMode(BlendMode.SourceAlpha);

    imageFrameLowerLeft.createOnGlThread(
        context, "models/frame_lower_left.obj", "models/frame_base.png");
    imageFrameLowerLeft.setMaterialProperties(0.0f, 3.5f, 1.0f, 6.0f);
    imageFrameLowerLeft.setBlendMode(BlendMode.SourceAlpha);

    imageFrameLowerRight.createOnGlThread(
        context, "models/frame_lower_right.obj", "models/frame_base.png");
    imageFrameLowerRight.setMaterialProperties(0.0f, 3.5f, 1.0f, 6.0f);
    imageFrameLowerRight.setBlendMode(BlendMode.SourceAlpha);

    heart.createOnGlThread(context, "models/heart.obj", "models/red.png");
    heart.setMaterialProperties(0.0f, 3.5f, 1.0f, 6.0f);
    heart.setBlendMode(BlendMode.SourceAlpha);

    diamond.createOnGlThread(context, "models/diamond.obj", "models/red.png");
    diamond.setMaterialProperties(0.0f, 3.5f, 1.0f, 6.0f);
    diamond.setBlendMode(BlendMode.SourceAlpha);

    club.createOnGlThread(context, "models/diamond.obj", "models/black.png");
    club.setMaterialProperties(0.0f, 3.5f, 1.0f, 6.0f);
    club.setBlendMode(BlendMode.SourceAlpha);

    spade.createOnGlThread(context, "models/diamond.obj", "models/black.png");
    spade.setMaterialProperties(0.0f, 3.5f, 1.0f, 6.0f);
    spade.setBlendMode(BlendMode.SourceAlpha);
  }

  public void draw(
      float[] viewMatrix,
      float[] projectionMatrix,
      AugmentedImage augmentedImage,
      Anchor centerAnchor,
      float[] colorCorrectionRgba,
      int cardType) {
    float[] tintColor =
        convertHexToColor(TINT_COLORS_HEX[augmentedImage.getIndex() % TINT_COLORS_HEX.length]);

    Pose[] localBoundaryPoses = {
      Pose.makeTranslation(
          -0.5f * augmentedImage.getExtentX(),
          0.0f,
          -0.5f * augmentedImage.getExtentZ()), // upper left
      Pose.makeTranslation(
          0.5f * augmentedImage.getExtentX(),
          0.0f,
          -0.5f * augmentedImage.getExtentZ()), // upper right
      Pose.makeTranslation(
          0.5f * augmentedImage.getExtentX(),
          0.0f,
          0.5f * augmentedImage.getExtentZ()), // lower right
      Pose.makeTranslation(
          -0.5f * augmentedImage.getExtentX(),
          0.0f,
          0.5f * augmentedImage.getExtentZ()), // lower left
      Pose.makeTranslation(0.0f, 0.0f, -1.00f * augmentedImage.getExtentZ())
    };

    Pose anchorPose = centerAnchor.getPose();
    Pose[] worldBoundaryPoses = new Pose[4];
    for (int i = 0; i < 4; ++i) {
      worldBoundaryPoses[i] = anchorPose.compose(localBoundaryPoses[i]);
    }

    float scaleFactor = 1.0f;
    float[] modelMatrix = new float[16];

    worldBoundaryPoses[0].toMatrix(modelMatrix, 0);
    imageFrameUpperLeft.updateModelMatrix(modelMatrix, scaleFactor);
    imageFrameUpperLeft.draw(viewMatrix, projectionMatrix, colorCorrectionRgba, tintColor);

    worldBoundaryPoses[1].toMatrix(modelMatrix, 0);
    imageFrameUpperRight.updateModelMatrix(modelMatrix, scaleFactor);
    imageFrameUpperRight.draw(viewMatrix, projectionMatrix, colorCorrectionRgba, tintColor);

    worldBoundaryPoses[2].toMatrix(modelMatrix, 0);
    imageFrameLowerRight.updateModelMatrix(modelMatrix, scaleFactor);
    imageFrameLowerRight.draw(viewMatrix, projectionMatrix, colorCorrectionRgba, tintColor);

    worldBoundaryPoses[3].toMatrix(modelMatrix, 0);
    imageFrameLowerLeft.updateModelMatrix(modelMatrix, scaleFactor);
    imageFrameLowerLeft.draw(viewMatrix, projectionMatrix, colorCorrectionRgba, tintColor);

    switch(cardType) {
        case 1: {
            worldBoundaryPoses[4].toMatrix(modelMatrix, 0);
            heart.updateModelMatrix(modelMatrix, scaleFactor);
            heart.draw(viewMatrix, projectionMatrix, colorCorrectionRgba, tintColor);
        }
        case 2: {
            worldBoundaryPoses[4].toMatrix(modelMatrix, 0);
            diamond.updateModelMatrix(modelMatrix, scaleFactor);
            diamond.draw(viewMatrix, projectionMatrix, colorCorrectionRgba, tintColor);
        }
        case 3: {
            worldBoundaryPoses[4].toMatrix(modelMatrix, 0);
            club.updateModelMatrix(modelMatrix, scaleFactor);
            club.draw(viewMatrix, projectionMatrix, colorCorrectionRgba, tintColor);
        }
        case 4: {
            worldBoundaryPoses[4].toMatrix(modelMatrix, 0);
            spade.updateModelMatrix(modelMatrix, scaleFactor);
            spade.draw(viewMatrix, projectionMatrix, colorCorrectionRgba, tintColor);
        }

    }

  }

  private static float[] convertHexToColor(int colorHex) {
    // colorHex is in 0xRRGGBB format
    float red = ((colorHex & 0xFF0000) >> 16) / 255.0f * TINT_INTENSITY;
    float green = ((colorHex & 0x00FF00) >> 8) / 255.0f * TINT_INTENSITY;
    float blue = (colorHex & 0x0000FF) / 255.0f * TINT_INTENSITY;
    return new float[] {red, green, blue, TINT_ALPHA};
  }
}
