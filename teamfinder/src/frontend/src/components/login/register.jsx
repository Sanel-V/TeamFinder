import React from "react";
import loginImg from "../../login.svg";
import axios from "axios";

export class Register extends React.Component {

    constructor(props) {
        super(props);

        this.baseUrl = "http://localhost:8080";

        this.state = {
            email: '',
            password: '',
            access_token: '',
            refresh_token: ''
        };

        this.handleChange = this.handleChange.bind(this);
        this.submit = this.submit.bind(this);
        

    }

    submit() {
        const { email, password } = this.state;
        console.log('email:'+email);
        console.log('pass:'+password);
        const params = new URLSearchParams()
        params.append('username', email)
        params.append('password', password)
        axios.post(this.baseUrl + '/account/add', params)
        .then(response => {
            console.log({response});
            if (response.status == 200){
                localStorage.setItem('access_token', response.data.access_token);
                window.location.href = "/accounts";
            }
        })
        .catch(err => {
            console.log({err});
        });
    }

    handleChange(e) {
        const {name, value} = e.target;
        this.setState({[name]: value});
    }

    trigger = (event) => {
        this.props.toggleActive(true);
        event.preventDefault();
    }

    render() {
        const {email, password} = this.state;
        return <div className="base-container">
            <div className="header">Register</div>
            <div className="content">
                <div className="image">
                    
                </div>
                <div className="form">
                    <div className="form-group">
                        <label htmlFor="email">Email</label>
                        <input type="email" name="email" placeholder="Email" value={email} onChange={this.handleChange}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Password</label>

                        <input type="password" name="password" placeholder="Password" value={password} onChange={this.handleChange}/>
                    </div>
                </div>
                <div className="switch">Already have account? <div className="switchButton" onClick = {this.trigger.bind(this)}>Login here.</div></div>
            </div>
            <div className="footer">
                <button type="button" onClick={this.submit} className="btn">Register</button>
            </div>        
        </div>
    }
}