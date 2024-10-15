<template>
  <div class="center">
    <h1 style="font-size: 30px; color: black;">Sign in/ Sign up</h1>
    <div class="container">
      <div :class="mainPanel">

        <div class="form-style" v-if="status == 'signIn'">
          <h2 style="font-size: 25px; color: black">Sign in</h2>
          <div>
            <el-icon><User /></el-icon>
            <input type="text" placeholder="Account" style="margin-left: 10px;">
          </div>
          <div>
            <el-icon color="black"><Lock /></el-icon>
            <input type="text" placeholder="Password" style="margin-left: 10px;">
          </div>
          <h3 style="font-size: 12px">Forgot your password?</h3>
          <button class="submit-button" @click="signIn">Sign in</button>
        </div>

        <div class="form-style" v-if="status == 'signUp'">
          <h2 style="font-size: 25px; color: black">Registered Account</h2>
          <div>
            <el-icon><User /></el-icon>
            <input type="text" placeholder="Account" style="margin-left: 10px;">
          </div>
          <div>
            <el-icon color="black"><Lock /></el-icon>
            <input type="text" placeholder="Password" style="margin-left: 10px;">
          </div>
          <button class="submit-button" @click="signUp">Sign up</button>
        </div>

      </div>
      <div :class="overlay">
        <div class="form-style" v-if="status == 'signIn'">
          <h2 class="overlaytitleH2">Welcome to SUSTech Open Course Website!</h2>
          <p class="overlaytitleP">
            Don't have an account?
          </p>
          <p class="overlaytitleP">
            Sign up and start journey now!
          </p>
          <div class="switch-button" @click="switchToSignUpPanel">Register</div>
        </div>
        <div class="form-style" v-if="status == 'signUp'">
          <h2 class="overlaytitleH2">Welcome Back!</h2>
          <p class="overlaytitleP">To keep connected with us please login with your personal info</p>
          <div class="switch-button" @click="switchToSignInPanel">Sign in</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  components: {},
  data() {
    return {
      mainPanel: 'mainPanel-signIn',
      overlay: 'overlay-signUp',
      status: 'signIn'
    }
  },
  methods: {
    switchToSignUpPanel() {
      console.log("Switch to sign up panel")
      this.mainPanel = "mainPanel-signUp"
      this.overlay = "overlay-signIn"
      setTimeout(() => {
        this.status = 'signUp'
      }, 200)
    },
    switchToSignInPanel() {
      console.log("Switch to sign in panel")
      this.mainPanel = "mainPanel-signIn"
      this.overlay = "overlay-signUp"
      setTimeout(() => {
        this.status = 'signIn'
      }, 200)
    },
    signIn(){
      alert('登录成功！');
    },
    signUp(){
      alert('注册成功！');
    },
  }
}
</script>

<style>
.center {
  width: 1920px;
  height: 1080px;
  background-size: 100% 100%;
  background-repeat: no-repeat;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

.container {
  background-color: #fff;
  border-radius: 10px;
  box-shadow: 0 14px 28px rgba(0, 0, 0, 0.25), 0 10px 10px rgba(0, 0, 0, 0.22);
  /* position: relative;
  overflow: hidden; */
  width: 768px;
  max-width: 100%;
  min-height: 480px;
  margin-top: 20px;
  display: flex;
  background: linear-gradient(135deg, #292a3a, #536976);
}

.mainPanel-signUp {
  border-radius: 0px 10px 10px 0px;
  width: 50%;
  height: 100%;
  background-color: #fff;
  transform: translateX(100%);
  transition: transform 0.6s ease-in-out;
  display: flex;
  align-items: center;
  justify-content: center;
}

.mainPanel-signIn {
  border-radius: 10px 0 0 10px;
  width: 50%;
  height: 100%;
  background-color: #fff;
  transform: translateX(0%);
  transition: transform 0.6s ease-in-out;
  display: flex;
  align-items: center;
  justify-content: center;
}

.overlaytitleH2 {
  font-size: 25px;
  color: #fff;
  margin-top: 20px;
  margin-left: 10px;
  margin-right: 10px;
}

.overlaytitleP {
  font-size: 15px;
  color: #fff;
  margin-top: 20px;
}

.overlay-signUp {
  border-radius: 0px 10px 10px 0px;
  width: 50%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0);
  display: flex;
  align-items: center;
  justify-content: center;
  transform: translateX(0%);
  transition: transform 0.6s ease-in-out;
}

.overlay-signIn {
  border-radius: 0px 10px 10px 0px;
  width: 50%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0);
  display: flex;
  align-items: center;
  justify-content: center;
  transform: translateX(-100%);
  transition: transform 0.6s ease-in-out;
}


.form-style {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
}

.switch-button {
  width: 180px;
  height: 40px;
  border-radius: 50px;
  border: 1px solid #fff;
  color: #fff;
  font-size: 15px;
  text-align: center;
  line-height: 40px;
  margin-top: 40px;
  cursor: pointer;
  transition: .3s;
}

.switch-button:hover {
  transform: scale(1.1);
}

.overlaylongH2 {
  font-size: 25px;
  color: black;
  /* width: 250px; */
}

input {
  background-color: #eee;
  border: none;
  padding: 12px 15px;
  margin: 10px 0;
  width: 240px;
}
h3{
  font-size: 10px;
  margin-top: 10px;
  cursor: pointer;
}

.inupbutton{
  background-color: #29eac4;
  border: none;
  width: 180px;
  height: 40px;
  border-radius: 50px;
  font-size: 15px;
  color: #fff;
  text-align: center;
  line-height: 40px;
  margin-top: 30px;
  cursor: pointer;
}

.submit-button {
  margin-left: 60%;
  margin-top: 50px;
  width: 180px;
  height: 40px;
  transform: translate(-50%, -50%);
  text-align: center;
  line-height: 40px;
  color: #fff;
  font-size: 15px;
  text-decoration: none;
  font-family: sans-serif;
  box-sizing: border-box;
  background: linear-gradient(90deg, #03a9f4, #f441a5, #ffeb3b, #03a9f4);
  border: transparent;
  border-radius: 50px;
  background-size: 400%;
  cursor: pointer;
  z-index: 1;
}

.submit-button:hover {
  animation: animate 8s linear infinite;
}
@keyframes animate{
  from{
    background-position: 0%;
  }
  to {
    background-position: 400%;
  }
}
.submit-button::before{
  content: '';
  position: absolute;
  inset: 0px;
  z-index: -1;
  background: linear-gradient(90deg, #03a9f4, #f441a5, #ffeb3b, #03a9f4);
  background-size: 400%;
  border-radius: 5px;
  opacity: 0;
}
.submit-button:hover::before{
  filter: blur(10px);
  opacity: 1;
  animation: animate 8s linear infinite;
}


</style>