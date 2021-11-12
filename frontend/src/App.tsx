import { Box } from '@chakra-ui/react';
import { Route, Switch } from 'react-router-dom';
import CodeBox from './components/CodeBox';
import Header from './components/Header';
import NotFound from './components/NotFound';
import Poll from './components/Poll';
import Profile from './components/Profile';
import SignIn from './components/SignIn';
import SignUp from './components/SignUp';
import { AuthProvider } from './context/AuthContext';

const App = (): JSX.Element => {
    return (
        <AuthProvider>
            <Box h="100vh" bgColor="red.100">
                <Header />
                <Switch>
                    <Route path="/signup">
                        <SignUp />
                    </Route>
                    <Route path="/signin">
                        <SignIn />
                    </Route>
                    <Route path="/profile">
                        <Profile />
                    </Route>
                    <Route exact path="/polls">
                        <Poll />
                    </Route>
                    <Route path="/polls/:id">
                        <Poll />
                    </Route>
                    <Route exact path="/">
                        <CodeBox />
                    </Route>
                    <Route>
                        <NotFound />
                    </Route>
                </Switch>
            </Box>
        </AuthProvider>
    );
};

export default App;
