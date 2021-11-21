import React from "react";
import "./profile.scss"
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from "axios";
import { Routes, Route, Link } from "react-router-dom";
import App from "../../App";
import { Accounts } from "../accounts/accounts";

export class Profile extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            profile: []
        }

        this.baseUrl = "http://localhost:8080";
        this.state = { 
            key: 0,
            image:"https://i.pinimg.com/564x/d5/42/0d/d5420d23ea3e6f092d8d1c60843400de.jpg",
            email : "defaul@email.hu",
            bio : "lorem"
        };

        axios.get(`${this.baseUrl}/account/get/`+props.id)
            .then(response => {
                this.state = { profile: response.data };
                console.log(props.id);
            })
            .catch(function (error) {
                // handle error

                console.log(error);
            })
            .then(function () {
                // always executed
            });

            console.log(this.state);

    }

    render() {
        return(
        <div className="container">
          
            <Routes>
              <Route path="allprofiles" element={<Accounts />} />
            </Routes>
            <div className="main-body">
            
                  <nav aria-label="breadcrumb" className="main-breadcrumb">
                    <ol className="breadcrumb">
                      <li className="breadcrumb-item"><a href="index.html">Home</a></li>
                      <li className="breadcrumb-item"><a href="javascript:void(0)">User</a></li>
                      <li className="breadcrumb-item active" aria-current="page">User Profile</li>
                    </ol>
                  </nav>
            
                  <AllData/>

            </div>
        </div>
        )

    }
}

function NoData(){
  return(
    <div className="col-md-12 mb-3">
    <div className="card">
      <div className="card-body">
        <div className="d-flex flex-column align-items-center text-center">
          <div className="mt-3">
            <h4>No data on this user</h4>
                <Link to='/allprofiles' > To the homePage </Link>
          </div>
        </div>
      </div>
    </div>
  </div>
  );
}

function AllData(){
  
  return(
      <div className="row gutters-sm">
      <div className="col-md-6 mb-3">
        <div className="card">
          <div className="card-body">
            <div className="d-flex flex-column align-items-center text-center">
              <img src="https://bootdey.com/img/Content/avatar/avatar7.png" alt="Admin" className="rounded-circle" width="150"></img>
              <div className="mt-3">
                <h4>John Doe</h4>
                <p className="text-secondary mb-1">Full Stack Developer</p>
                <p className="text-muted font-size-sm">Bay Area, San Francisco, CA</p>
                <button className="btn btn-primary">Follow</button>
                <button className="btn btn-outline-primary">Message</button>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="col-md-6">
        <div className="card mb-3">
          <div className="card-body">
            <div className="row">
              <div className="col-sm-3">
                <h6 className="mb-0">Full Name</h6>
              </div>
              <div className="col-sm-9 text-secondary">
                Kenneth Valdez
              </div>
            </div>
            <hr></hr>
            <div className="row">
              <div className="col-sm-3">
                <h6 className="mb-0">Email</h6>
              </div>
              <div className="col-sm-9 text-secondary">
                fip@jukmuh.al
              </div>
            </div>
            <hr></hr>
            <div className="row">
              <div className="col-sm-3">
                <h6 className="mb-0">Phone</h6>
              </div>
              <div className="col-sm-9 text-secondary">
                (239) 816-9029
              </div>
            </div>
            <hr></hr>
            <div className="row">
              <div className="col-sm-3">
                <h6 className="mb-0">Address</h6>
              </div>
              <div className="col-sm-9 text-secondary">
                Bay Area, San Francisco, CA
              </div>
            </div>
            <hr></hr>
            <div className="row">
              <div className="col-sm-12">
                <a className="btn btn-info " target="__blank" href="https://www.bootdey.com/snippets/view/profile-edit-data-and-skills">Edit</a>
              </div>
            </div>
          </div>
        </div>

      </div>

      <div className="col-md-12 mb-3">
        <div className="card">
          <div className="card-body">
            <div className="d-flex flex-column align-items-center text-center">
              <div className="my-2">
                  <div className="row">
                          <h6 className="mb-0 float-left">About me</h6>
                      <div style={{textAlign: "justify"}} className="text-secondary" >
                          Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptas quas vitae earum optio sint, inventore nulla nesciunt sunt nam dolores in laudantium maxime, amet, aspernatur eos illum! Nam, quam explicabo!
                          Lorem ipsum dolor sit amet consectetur adipisicing elit. Voluptas quas vitae earum optio sint, inventore nulla nesciunt sunt nam dolores in laudantium maxime, amet, aspernatur eos illum! Nam, quam explicabo!
                      </div>
                  </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div className="mb-3">
        <div className="card">
          <div className="card-body">
            <div className="d-flex flex-column align-items-center text-center">
              <div className="my-2">
                  <div className="row">
                          <h6 className="mb-0 float-left">Tags</h6>
                      <div className="text-secondary" >
                      </div>
                  </div>
              </div>
            </div>
          </div>
        </div>
      </div>

    </div>

    );
}