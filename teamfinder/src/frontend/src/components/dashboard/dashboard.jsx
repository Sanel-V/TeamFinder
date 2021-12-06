import React from "react";
import "./dashboard.scss";
import { Login, Register } from "../login/index";

class Dashboard extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      isLogginActive: true
    };
    this.toggleActive = this.toggleActive.bind(this);
  }

  componentDidMount() {
    //Add .right by default
    // this.rightSide.classList.add("right");
  }

  changeState() {
    const { isLogginActive } = this.state;

    if (isLogginActive) {
      this.rightSide.classList.remove("right");
      this.rightSide.classList.add("left");
    } else {
      this.rightSide.classList.remove("left");
      this.rightSide.classList.add("right");
    }
    this.setState(prevState => ({ isLogginActive: !prevState.isLogginActive }));
  }

  toggleActive (isActive) {
    this.setState({isLogginActive: isActive})
  }

  render() {
    const { isLogginActive } = this.state;
    const current = isLogginActive ? "Register" : "Login";
    const currentActive = isLogginActive ? "login" : "register";
    return (
      <div className="App">
        <div className="login">
          <div className="container" ref={ref => (this.container = ref)}>
            { isLogginActive
              && (<Login toggleActive={this.toggleActive.bind(this)}/>)
            }
             { !isLogginActive
              && (<Register toggleActive={this.toggleActive.bind(this)}/>)
            }
          </div>
        </div>
      </div>
    );
  }
}

export default Dashboard;