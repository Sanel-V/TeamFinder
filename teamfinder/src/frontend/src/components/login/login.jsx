import React from "react";
import loginImg from "../../login.svg";
import axios from "axios";

export class Login extends React.Component {

    constructor(props) {
        super(props);

        this.baseUrl = "http://localhost:8080";

        this.state = {
            email: '',
            password: ''
        };

        this.handleChange = this.handleChange.bind(this);
        this.submit = this.submit.bind(this);

    }

    submit() {
        const { email, password } = this.state;
        axios.post(this.baseUrl + '/accounts/login', { email: email, password: password })
        .then(response => {
            console.log({response});
        })
        .catch(err => {
            console.log({err});
        });
    }

    handleChange(e) {
        const {name, value} = e.target;
        this.setState({[name]: value});
    }

    render() {
        const {email, password} = this.state;
        return <div className="base-container">
            <div className="header">Login</div>
            <div className="content">
                <div className="image">
                    <img src={loginImg} />
                </div>
                <div className="form">
                    <div className="form-group">
                        <label htmlFor="email">Email</label>
                        <input type="text" name="email" placeholder="Email" value={email} onChange={this.handleChange}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Password</label>
                        <input type="password" name="password" placeholder="Password" value={password} onChange={this.handleChange}/>
                    </div>
                </div>
            </div>
            <div className="footer">
                <button type="button" onClick={this.submit} className="btn">Login</button>
            </div>        
        </div>
    }
}