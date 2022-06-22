import { Alert, AlertTitle, Box, Button, TextField } from '@mui/material'
import React, { useEffect, useState } from 'react'
import { getPostsBySellerIdRequest, savePostRequest } from '../api/controllers/post-controller'
import PostCard from './PostCard'

const StorePost = ({ myProfile, enqueueSnackbar, sellerId }) => {
    //GET STORE POST LIST
    const [storePostList, setStorePostList] = useState([])
    const fetchStorePosts = async () => {
        try {
            let res = await getPostsBySellerIdRequest(sellerId);
            if (res) {
                setStorePostList(res.data);
            }
        } catch (error) {
            if (error.response.status === 401) {
                enqueueSnackbar('Lütfen giriş yapın!', { variant: 'error' });
            } else if (error.response.status === 403) {
                enqueueSnackbar('Bu işlem için yetkiniz bulunmuyor!', { variant: 'error' });
            } else {
                enqueueSnackbar('Bir hata meydana geldi. Daha sonra tekrar deneyin!', { variant: 'error' });
            }
        }
    }

    //ADD STORE POST
    const [post, setPost] = useState({
        content: "",
    })

    const handleAddPost = async () => {
        try {
            let res = await savePostRequest(post);
            if (res) {
                enqueueSnackbar('Başarıyla paylaşım yaptın!', { variant: 'success' });
                setPost({
                    content: "",
                })
                fetchStorePosts()
            }
        } catch (error) {
            if (error.response.status === 401) {
                enqueueSnackbar('Lütfen giriş yapın!', { variant: 'error' });
            } else if (error.response.status === 403) {
                enqueueSnackbar('Bu işlem için yetkiniz bulunmuyor!', { variant: 'error' });
            } else {
                enqueueSnackbar('Bir hata meydana geldi. Daha sonra tekrar deneyin!', { variant: 'error' });
            }
        }
    }

    useEffect(() => {
        fetchStorePosts();
    }, [])

    return (
        <>
            {
                myProfile &&
                <Box sx={{ display: 'flex', flexDirection: 'column', gap: '1rem', }}>
                    <TextField fullWidth variant='outlined' label="Paylaşmak istediklerinizi yazın" multiline minRows={2} onChange={(e) => {
                        post.content = e.target.value
                        setPost({ ...post })
                    }} />
                    <Box sx={{ display: 'flex', justifyContent: 'flex-end' }}>
                        <Button disabled={!post.content} variant='outlined' onClick={() => handleAddPost()}>Paylaş</Button>
                    </Box>
                </Box>
            }
            {
                storePostList.length > 0 ?
                    storePostList.map((val, i) =>
                        <PostCard key={i} postData={val} />
                    ) :
                    <Alert severity="warning">
                        <AlertTitle>Henüz paylaşım yapılmamış :(</AlertTitle>
                        Bu mağaza henüz paylaşım yapmamış.
                    </Alert>
            }
        </>
    )
}

export default StorePost;