import React from "react";

export class Register extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            username: '',
            email: '',
            password: ''
        };

        this.handleChange = this.handleChange.bind(this);
        this.submit = this.submit.bind(this);
    }

    submit() {
        const {username, email, password} = this.state;
        console.log(this.state);
    }

    handleChange(e) {
        const {name, value} = e.target;
        this.setState({[name]: value});
    }

    render() {
        const {username, email, password} = this.state;
        return <div className="base-container">
            <div className="header">Register</div>
            <div className="content">
                <div className="image">
                    
                </div>
                <div className="form">
                    <div className="form-group">
                        <label htmlFor="username">Username</label>
                        <input type="text" name="username" placeholder="Username" value={username} onChange={this.handleChange}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="email">Email</label>
                        <input type="email" name="email" placeholder="Email" value={email} onChange={this.handleChange}/>
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Password</label>
                        <input type="password" name="password" placeholder="Password" value={password} onChange={this.handleChange}/>
                    </div>
                </div>
            </div>
            <div className="footer">
                <button type="button" onClick={this.submit} className="btn">Register</button>
            </div>        
        </div>
    }
}