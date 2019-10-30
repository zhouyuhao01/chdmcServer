import React from 'react';
import './App.css';
import request from 'request';
import {HashRouter as Router,Link, Route, Switch} from 'react-router-dom';
import { Layout, Menu, Icon } from 'antd';
const { Header, Sider, Content } = Layout;

const Home = () => (
    <div>
        <h2>首页</h2>
    </div>
)
const About = () => (
    <div>
        <h2>关于</h2>
    </div>
)


class App extends  React.Component{

    constructor(props) {
        super(props);
        this.state = {
            path:"/"
        }
    }

    componentDidMount() {
        let url = "http://121.40.150.25:8080/hello";
        request(url, (error, response, body) => {
            console.log('error:', error); // Print the error if one occurred
            console.log('statusCode:', response && response.statusCode); // Print the response status code if a response was received
            console.log('body:', body); // Print the HTML for the Google homepage.
        })
    }

    render()  {
        return <Router>

            <Layout>
                <Sider >
                    <Header  style={{ background:'#02223f', padding: 0 }} >
                        <h1 style={{textAlign:"center", color:"#fff", fontSize:"30px"}} >LOGO</h1>
                    </Header>

                    <Menu theme="dark" mode="inline" defaultSelectedKeys={['1']}>
                        <Menu.Item key="3">
                            <Link to="/">
                                <Icon type="home" />
                                <span>首页</span>
                            </Link>
                        </Menu.Item>

                        <Menu.Item key="1">
                            <Link to="/about">
                                <Icon type="user" />
                                <span>关于</span>
                            </Link>
                        </Menu.Item>
                    </Menu>
                </Sider>
                <Content>
                    <Header  style={{ background:'#fff', padding: 0 }} />
                    <Content
                        style={{
                            margin: '24px 16px',
                            padding: 24,
                            background: '#fff',
                            minHeight: 280,
                        }}
                    >
                        <div>
                            <Switch >
                                <Route extra path="/" component={Home} />
                                <Route path="/about" component={About} />
                            </Switch>
                        </div>
                    </Content>

                </Content>
            </Layout>



        </Router>
    };
}

export default App;
