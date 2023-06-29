<template>
  <div>
    <div @click="handleImages" class="uploader">
      <UploadImages ref="file"></UploadImages>
    </div>
  </div>
</template>

<script>
import AWS from "aws-sdk";
import { v4 as uuidv4 } from "uuid";
import UploadImages from "vue-upload-drop-images";
export default {
  components: {
    UploadImages,
  },
  props: {
    dir: String,
    idx: Number,
  },
  data() {
    return {
      file: [],
      // albumBucketName: "",
      // bucketRegion: "",
      // IdentityPoolId: "",
    };
  },
  methods: {
    handleImages() {
      this.file = this.$refs.file.files;
    },
    async upload() {
      AWS.config.update({
        region: this.bucketRegion,
        credentials: new AWS.CognitoIdentityCredentials({
          IdentityPoolId: this.IdentityPoolId,
        }),
      });

      const s3 = new AWS.S3({
        apiVersion: "2006-03-01",
        params: { Bucket: this.albumBucketName },
      });
      let albumPhotosKey = encodeURIComponent(this.dir) + "/";
      var url = [];
      for (let i = 0; i < this.file.length; i++) {
        let photoKey = albumPhotosKey + uuidv4() + ".png";
        s3.upload(
          {
            Key: photoKey,
            Body: this.file[0],
            ACL: "public-read",
          },
          (err, data) => {
            if (err) {
              return alert("There was an error uploading your photo: ", err.message);
            }
            url.push(data.Location);
            if (url.length == this.file.length) {
              this.$emit("pushEvent", data.Location);
            }
          }
        );
      }
    },
  },
  watch: {
    idx() {
      this.upload();
    },
  },
};
</script>

<style scoped>
.upload-desc {
  padding: 1rem 0;
  color: rgb(240, 108, 94);
}
.uploader {
  margin: 0.5rem 0;
}
</style>
