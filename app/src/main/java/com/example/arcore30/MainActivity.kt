package com.example.arcore30

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.ar.core.Anchor
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.animation.ModelAnimator

import com.google.ar.sceneform.Node
import com.google.ar.sceneform.math.Quaternion
import com.google.ar.sceneform.math.Vector3
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    lateinit var arFragment: ArFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        arFragment = fragment as ArFragment

        arFragment.setOnTapArPlaneListener { hitResult, _, _ ->
            Toast.makeText(this, "Get Click", Toast.LENGTH_LONG).show()

            // val randomId=R.raw.beedrill
          //  val randomId = R.raw.fly   //fly  beautiful bird without color
            val randomId = R.raw.skeb   // red eagle

            loadModelAndAddToScene(hitResult.createAnchor(), randomId)

        }
    }

    private fun loadModelAndAddToScene(ancore: Anchor, modelResourceId: Int) {
        ModelRenderable.builder()
            .setSource(this, modelResourceId)
            .build()
            .thenAccept {
                val spaceShip = when (modelResourceId) {

                    R.raw.beedrill -> Model.Bee
                    R.raw.fly -> Model.Fly  //fly  beautiful bird without color
                    R.raw.skeb -> Model.Skeb // red eagle
                    else -> Model.Bee
                }
                addNodeToScene(ancore, it, spaceShip)
                Toast.makeText(this, "Loded", Toast.LENGTH_LONG).show()

            }.exceptionally {
                Toast.makeText(this, "Error in loading Model", Toast.LENGTH_LONG).show()
                null
            }

    }

    private fun addNodeToScene(
        anchor: Anchor,
        modelRenderable: ModelRenderable,
        model: Model
    ) {

        /* //bee
         val speed=50f
         val rotateX=-60f
         val rotateY=0f
         val rotateZ=0f*/
       /* //fly  beautiful bird without color
        val speed = 20f
        val rotateX = 0f
        val rotateY = 0f
        val rotateZ = 0f*/
        //skeb  red eagle
        val speed = 40f
        val rotateX = 0f
        val rotateY = 0f
        val rotateZ = 0f


        val anchorNode = AnchorNode(anchor)
        val rotatingNode = RotatingNode(speed).apply {
            setParent(anchorNode)
        }
        Node().apply {
            renderable = modelRenderable
            setParent(rotatingNode)
            localPosition = Vector3(model.radius, model.height, 0f)         //X,Y,Z
            // Y axis points Up !!!


            localRotation = Quaternion.eulerAngles(Vector3(rotateX, rotateY, rotateZ))
            startAnimation(renderable as ModelRenderable)
        }
        arFragment.arSceneView.scene.addChild(anchorNode)

    }

    private fun startAnimation(renderable: ModelRenderable) {


        // val animationString="Beedrill_Animation"
      //  val animationString = "fly"
        val animationString = "skeb"

        //  val animationString="skeb"


        if (renderable.animationDataCount == 0) {
            return
        }
        val animationData = renderable.getAnimationData(animationString)
        ModelAnimator(animationData, renderable).apply {
            repeatCount = ModelAnimator.INFINITE
            start()
        }
    }


}



