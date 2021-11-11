import { Button, Center, FormControl, FormLabel, Heading, Input, Link, Spinner, VStack, Text } from '@chakra-ui/react';
import React, { useContext, useEffect } from 'react';
import { SubmitHandler, useForm, useFormState } from 'react-hook-form';
import { authenticate } from '../api/api';
import { useAuth } from '../context/AuthContext';
import { setLocalStorageItem } from '../utils';
import Section from './Section';

interface Inputs {
    username: string;
    password: string;
}

const SignIn = (): JSX.Element => {
    const authContext = useAuth();
    const {
        register,
        handleSubmit,
        formState: { errors, isSubmitting },
    } = useForm<Inputs>();

    const onSubmit: SubmitHandler<Inputs> = async (data) => {
        authContext.login(data.username, data.password);
    };

    return (
        <Center>
            <Section mt="10%" w="40%">
                {authContext.isAuthenticated && (
                    <>
                        <Center>
                            <Text>You are already signed in: {authContext.account?.name}</Text>
                        </Center>
                        <Center>
                            <Button onClick={authContext.logout}>Sign out</Button>
                        </Center>
                    </>
                )}
                {!authContext.isAuthenticated && (
                    <>
                        <Heading mb="2rem" color="red.500">
                            Sign in
                        </Heading>
                        <form onSubmit={handleSubmit(onSubmit)}>
                            <VStack spacing="1rem">
                                <FormControl>
                                    <FormLabel>Brukernavn</FormLabel>
                                    <Input
                                        id="username"
                                        placeholder="username"
                                        {...register('username', {
                                            required: 'This is required',
                                        })}
                                    />
                                </FormControl>
                                <FormControl>
                                    <FormLabel>Password</FormLabel>
                                    <Input
                                        id="password"
                                        placeholder="password"
                                        type="password"
                                        {...register('password', {
                                            required: 'This is required',
                                        })}
                                    />
                                </FormControl>
                                <Center>
                                    <Button type="submit" colorScheme="red" isLoading={isSubmitting}>
                                        Sign in
                                    </Button>
                                </Center>
                            </VStack>
                        </form>
                        <Center>{authContext.inProgress && <Spinner />}</Center>
                        <Center my="1rem">
                            <p>or</p>
                        </Center>
                        <Center>
                            <Link color="red" href="/signup">
                                Sign up
                            </Link>
                        </Center>
                    </>
                )}
            </Section>
        </Center>
    );
};

export default SignIn;
