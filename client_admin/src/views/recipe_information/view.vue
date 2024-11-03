<template>
  <el-main class="bg edit_wrap">
    <el-form
      ref="form"
      :model="form"
      status-icon
      label-width="120px"
      v-if="is_view()"
    >
      <el-col
        v-if="
          user_group === '管理员' ||
          $check_field('get', 'recipe_name') ||
          $check_field('add', 'recipe_name') ||
          $check_field('set', 'recipe_name')
        "
        :xs="24"
        :sm="12"
        :lg="8"
        class="el_form_item_warp"
      >
        <el-form-item label="食谱名称" prop="recipe_name">
          <el-input
            id="recipe_name"
            v-model="form['recipe_name']"
            placeholder="请输入食谱名称"
            v-if="
              user_group === '管理员' ||
              (form['recipe_information_id'] &&
                $check_field('set', 'recipe_name')) ||
              (!form['recipe_information_id'] &&
                $check_field('add', 'recipe_name'))
            "
            :disabled="disabledObj['recipe_name_isDisabled']"
          ></el-input>
          <div v-else-if="$check_field('get', 'recipe_name')">
            {{ form["recipe_name"] }}
          </div>
        </el-form-item>
      </el-col>
      <el-col
        v-if="
          user_group === '管理员' ||
          $check_field('get', 'recipe_classification') ||
          $check_field('add', 'recipe_classification') ||
          $check_field('set', 'recipe_classification')
        "
        :xs="24"
        :sm="12"
        :lg="8"
        class="el_form_item_warp"
      >
        <el-form-item label="食谱分类" prop="recipe_classification">
          <el-select
            id="recipe_classification"
            v-model="form['recipe_classification']"
            v-if="
              user_group === '管理员' ||
              (form['recipe_information_id'] &&
                $check_field('set', 'recipe_classification')) ||
              (!form['recipe_information_id'] &&
                $check_field('add', 'recipe_classification'))
            "
          >
            <el-option
              v-for="o in list_recipe_classification"
              :key="o['recipe_classification']"
              :label="o['recipe_classification']"
              :value="o['recipe_classification']"
            >
            </el-option>
          </el-select>
          <div v-else-if="$check_field('get', 'recipe_classification')">
            {{ form["recipe_classification"] }}
          </div>
        </el-form-item>
      </el-col>
      <el-col
        v-if="
          user_group === '管理员' ||
          $check_field('get', 'cover') ||
          $check_field('add', 'cover') ||
          $check_field('set', 'cover')
        "
        :xs="24"
        :sm="12"
        :lg="8"
        class="el_form_item_warp"
      >
        <el-form-item label="封面" prop="cover">
          <el-upload
            :disabled="disabledObj['cover_isDisabled']"
            id="cover"
            class="avatar-uploader"
            drag
            accept="image/gif, image/jpeg, image/png, image/jpg"
            action=""
            :http-request="upload_cover"
            :show-file-list="false"
            v-if="
              user_group === '管理员' ||
              (form['recipe_information_id'] && $check_field('set', 'cover')) ||
              (!form['recipe_information_id'] && $check_field('add', 'cover'))
            "
          >
            <img
              v-if="form['cover']"
              :src="$fullUrl(form['cover'])"
              class="avatar"
            />
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
          <el-image
            v-else-if="$check_field('get', 'cover')"
            style="width: 100px; height: 100px"
            :src="$fullUrl(form['cover'])"
            :preview-src-list="[$fullUrl(form['cover'])]"
          >
            <div slot="error" class="image-slot">
              <img
                src="../../../public/img/error.png"
                style="width: 90px; height: 90px"
              />
            </div>
          </el-image>
        </el-form-item>
      </el-col>
      <el-col
        v-if="
          user_group === '管理员' ||
          $check_field('get', 'taboo') ||
          $check_field('add', 'taboo') ||
          $check_field('set', 'taboo')
        "
        :xs="24"
        :sm="12"
        :lg="8"
        class="el_form_item_warp"
      >
        <el-form-item label="食用禁忌" prop="taboo">
          <el-input
            id="taboo"
            v-model="form['taboo']"
            placeholder="请输入食用禁忌"
            v-if="
              user_group === '管理员' ||
              (form['recipe_information_id'] && $check_field('set', 'taboo')) ||
              (!form['recipe_information_id'] && $check_field('add', 'taboo'))
            "
            :disabled="disabledObj['taboo_isDisabled']"
          ></el-input>
          <div v-else-if="$check_field('get', 'taboo')">
            {{ form["taboo"] }}
          </div>
        </el-form-item>
      </el-col>
      <el-col
        v-if="
          user_group === '管理员' ||
          $check_field('get', 'energy') ||
          $check_field('add', 'energy') ||
          $check_field('set', 'energy')
        "
        :xs="24"
        :sm="12"
        :lg="8"
        class="el_form_item_warp"
      >
        <el-form-item label="能量" prop="energy">
          <el-input
            id="energy"
            v-model="form['energy']"
            placeholder="请输入能量"
            v-if="
              user_group === '管理员' ||
              (form['recipe_information_id'] &&
                $check_field('set', 'energy')) ||
              (!form['recipe_information_id'] && $check_field('add', 'energy'))
            "
            :disabled="disabledObj['energy_isDisabled']"
          ></el-input>
          <div v-else-if="$check_field('get', 'energy')">
            {{ form["energy"] }}
          </div>
        </el-form-item>
      </el-col>
      <el-col
        v-if="
          user_group === '管理员' ||
          $check_field('get', 'intended_for') ||
          $check_field('add', 'intended_for') ||
          $check_field('set', 'intended_for')
        "
        :xs="24"
        :sm="12"
        :lg="8"
        class="el_form_item_warp"
      >
        <el-form-item label="适用人群" prop="intended_for">
          <el-input
            id="intended_for"
            v-model="form['intended_for']"
            placeholder="请输入适用人群"
            v-if="
              user_group === '管理员' ||
              (form['recipe_information_id'] &&
                $check_field('set', 'intended_for')) ||
              (!form['recipe_information_id'] &&
                $check_field('add', 'intended_for'))
            "
            :disabled="disabledObj['intended_for_isDisabled']"
          ></el-input>
          <div v-else-if="$check_field('get', 'intended_for')">
            {{ form["intended_for"] }}
          </div>
        </el-form-item>
      </el-col>
      <el-col
        v-if="
          user_group === '管理员' ||
          $check_field('get', 'make_video') ||
          $check_field('add', 'make_video') ||
          $check_field('set', 'make_video')
        "
        :xs="24"
        :sm="12"
        :lg="8"
        class="el_form_item_warp"
      >
        <el-form-item label="制作视频" prop="make_video">
          <el-upload
            v-if="
              user_group === '管理员' ||
              (form['recipe_information_id'] &&
                $check_field('set', 'make_video')) ||
              (!form['recipe_information_id'] &&
                $check_field('add', 'make_video'))
            "
            class="upload-demo"
            drag
            action=""
            style="max-width: 300px; width: 100%"
            :http-request="upload_make_video"
            :limit="1"
            accept="video/ogg,video/mp4,video/webm"
          >
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              将视频拖到此处，或<em>点击上传</em>
            </div>
          </el-upload>
          <div v-else-if="$check_field('get', 'make_video')">
            <el-button type="primary" @click="download(form['make_video'])"
              >下载<i class="el-icon-download el-icon--right"></i
            ></el-button>
          </div>
        </el-form-item>
      </el-col>
      <el-col
        v-if="
          user_group === '管理员' ||
          $check_field('get', 'recipe_introduction') ||
          $check_field('add', 'recipe_introduction') ||
          $check_field('set', 'recipe_introduction')
        "
        :xs="24"
        :sm="24"
        :lg="24"
        class="el_form_editor_warp"
      >
        <el-form-item label="食谱介绍" prop="recipe_introduction">
          <quill-editor
            v-model.number="form['recipe_introduction']"
            v-if="
              user_group === '管理员' ||
              (form['recipe_information_id'] &&
                $check_field('set', 'recipe_introduction')) ||
              (!form['recipe_information_id'] &&
                $check_field('add', 'recipe_introduction'))
            "
          >
          </quill-editor>
          <div
            v-else-if="$check_field('get', 'recipe_introduction')"
            v-html="form['recipe_introduction']"
          ></div>
        </el-form-item>
      </el-col>

      <el-col :xs="24" :sm="12" :lg="8" class="el_form_btn_warp">
        <el-form-item>
          <el-button type="primary" @click="submit()">提交</el-button>
          <el-button @click="cancel()">取消</el-button>
        </el-form-item>
      </el-col>
    </el-form>
  </el-main>
</template>

<script>
import mixin from "@/mixins/page.js";

export default {
  mixins: [mixin],
  data() {
    return {
      field: "recipe_information_id",
      url_add: "~/api/recipe_information/add?",
      url_set: "~/api/recipe_information/set?",
      url_get_obj: "~/api/recipe_information/get_obj?",
      url_upload: "~/api/recipe_information/upload?",

      query: {
        recipe_information_id: 0,
      },

      form: {
        recipe_name: "", // 食谱名称
        recipe_classification: "", // 食谱分类
        cover: "", // 封面
        taboo: "", // 食用禁忌
        energy: "", // 能量
        intended_for: "", // 适用人群
        make_video: "", // 制作视频
        recipe_introduction: "", // 食谱介绍
        recipe_information_id: 0, // ID
      },
      disabledObj: {
        recipe_name_isDisabled: false,
        recipe_classification_isDisabled: false,
        cover_isDisabled: false,
        taboo_isDisabled: false,
        energy_isDisabled: false,
        intended_for_isDisabled: false,
        make_video_isDisabled: false,
        recipe_introduction_isDisabled: false,
      },

      // 食谱分类选项列表
      list_recipe_classification: [""],
    };
  },
  methods: {
    /**
     * 获取食谱分类列表
     */
    async get_list_recipe_classification() {
      var json = await this.$get("~/api/recipe_classification/get_list?");
      if (json.result && json.result.list) {
        this.list_recipe_classification = json.result.list;
      } else if (json.error) {
        console.error(json.error);
      }
    },

    /**
     * 上传封面
     * @param {Object} param 图片参数
     */
    upload_cover(param) {
      this.uploadFile(param.file, "cover");
    },

    /**
     * 上传制作视频
     * @param {Object} param 视频参数
     */
    upload_make_video(param) {
      this.uploadFile(param.file, "make_video");
    },

    /**
     * 获取对象之前
     * @param {Object} param
     */
    get_obj_before(param) {
      var form = "";

      if (this.form && form) {
        Object.keys(this.form).forEach((key) => {
          Object.keys(form).forEach((dbKey) => {
            // if(dbKey === "charging_standard"){
            // 	this.form['charging_rules'] = form[dbKey];
            // 	this.disabledObj['charging_rules_isDisabled'] = true;
            // };
            if (key === dbKey) {
              this.disabledObj[key + "_isDisabled"] = true;
            }
          });
        });
      }
      $.db.del("form");
      return param;
    },

    /**
     * 获取对象之后
     * @param {Object} json
     * @param {Object} func
     */
    get_obj_after(json, func) {},

    /**
     * 提交前验证事件
     * @param {Object} 请求参数
     * @return {String} 验证成功返回null, 失败返回错误提示
     */
    submit_check(param) {
      let msg = null;
      return msg;
    },

    is_view() {
      var bl = this.user_group == "管理员";

      if (!bl) {
        bl = this.$check_action("/recipe_information/table", "add");
        console.log(
          bl ? "你有表格添加权限视作有添加权限" : "你没有表格添加权限"
        );
      }
      if (!bl) {
        bl = this.$check_action("/recipe_information/table", "set");
        console.log(
          bl ? "你有表格添加权限视作有修改权限" : "你没有表格修改权限"
        );
      }
      if (!bl) {
        bl = this.$check_action("/recipe_information/view", "add");
        console.log(
          bl ? "你有视图添加权限视作有添加权限" : "你没有视图添加权限"
        );
      }
      if (!bl) {
        bl = this.$check_action("/recipe_information/view", "set");
        console.log(
          bl ? "你有视图修改权限视作有修改权限" : "你没有视图修改权限"
        );
      }
      if (!bl) {
        bl = this.$check_action("/recipe_information/view", "get");
        console.log(
          bl ? "你有视图查询权限视作有查询权限" : "你没有视图查询权限"
        );
      }

      console.log(
        bl
          ? "具有当前页面的查看权，请注意这不代表你有字段的查看权"
          : "无权查看当前页，请注意即便有字段查询权限没有页面查询权限也不行"
      );

      return bl;
    },
    /**
     * 上传文件
     * @param {Object} param
     */
    uploadimg(param) {
      this.uploadFile(param.file, "avatar");
    },
  },
  created() {
    this.get_list_recipe_classification();
  },
};
</script>

<style scoped="scoped">
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
