<template>
  <div class="profile-container">
    <el-card class="box-card">
      <div class="user">
        <img :src="$fullUrl(user.avatar)" />
        <div class="user-info">
          <p class="name">{{ user.username }}</p>
          <p class="access">{{ user.user_group }}</p>
        </div>
      </div>

      <div class="login-info">
        <p>
          绰号<span>{{ user.nickname }}</span>
        </p>
        <p>
          上次登录时间：<span>{{
            $toTime(user.login_time, "yyyy-MM-dd hh:mm:ss")
          }}</span>
        </p>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: this.$store.state.user,
    };
  },
};
</script>

<style lang="less" scoped>
.user {
  display: flex;
  align-items: center;
  padding-bottom: 20px;
  margin-bottom: 20px;
  border-bottom: 1px solid #ccc;

  img {
    margin-right: 40px;
    width: 150px;
    height: 150px;
    border-radius: 50%;
    object-fit: cover;
  }

  .user-info {
    .name {
      font-size: 32px;
      margin-bottom: 10px;
    }
    .access {
      color: #999999;
    }
  }
}
.login-info {
  p {
    line-height: 28px;
    font-size: 14px;
    color: #999999;

    span {
      color: #666666;
      margin-left: 60px;
    }
  }
}
</style>